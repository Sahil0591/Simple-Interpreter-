import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

public class SimpleLangInterpreter extends AbstractParseTreeVisitor<Integer> implements SimpleLangVisitor<Integer> {

    private final Map<String, FunctionDetails> global_funcs = new HashMap<>();
    private final Stack<Map<String, Integer>> frames = new Stack<>();

    public class FunctionDetails {
        public final String name;
        public final List<SimpleLangParser.Typed_idfrContext> params;
        public final SimpleLangParser.BodyContext body;

        public FunctionDetails(String name, List<SimpleLangParser.Typed_idfrContext> params, SimpleLangParser.BodyContext body) {
            this.name = name;
            this.params = params;
            this.body = body;
        }
    }
    public Integer visitProgram(SimpleLangParser.ProgContext ctx, String[] args) {
        // Extract all declarations (functions)
        for (SimpleLangParser.DecContext dec : ctx.dec()) {
            String functionName = dec.typed_idfr(0).Idfr().getText();
            // Create a simple data structure to store function details
            FunctionDetails funcDetails = new FunctionDetails(
                    functionName,
                    dec.typed_idfr().subList(1, dec.typed_idfr().size()), // Parameters
                    dec.body() // Function body
            );
            global_funcs.put(functionName, funcDetails); // Store the processed function details
        }

        // Process the main function
        FunctionDetails mainFunction = global_funcs.get("main");
        Map<String, Integer> newFrame = new HashMap<>();

        // Bind arguments to parameters for the main function
        for (int i = 0; i < args.length; i++) {
            String paramName = mainFunction.params.get(i).Idfr().getText();
            int value = args[i].equals("true") ? 1 : args[i].equals("false") ? 0 : Integer.parseInt(args[i]);
            newFrame.put(paramName, value);
        }

        frames.push(newFrame);
        return visit(mainFunction.body); // Visit the body directly
    }

    @Override public Integer visitProg(SimpleLangParser.ProgContext ctx)
    {

        throw new RuntimeException("Should not be here!");

    }

    @Override public Integer visitDec(SimpleLangParser.DecContext ctx)
    {

        Integer returnValue = visit(ctx.body());
        frames.pop();
        return returnValue;

    }

    @Override public Integer visitTyped_idfr(SimpleLangParser.Typed_idfrContext ctx)
    {
        throw new RuntimeException("Should not be here!");
    }

    @Override public Integer visitType(SimpleLangParser.TypeContext ctx)
    {
        throw new RuntimeException("Should not be here!");
    }

    @Override
    public Integer visitBody(SimpleLangParser.BodyContext ctx) {
        for (SimpleLangParser.Typed_idfrContext varDec : ctx.vardec) {
            String varName = varDec.Idfr().getText();
            frames.peek().put(varName, 0); // Initialize with default value
        }
        return visit(ctx.ene());
    }
    @Override
    public Integer visitEne(SimpleLangParser.EneContext ctx) {
        Integer returnValue = null;

        // Check if ctx.exp() is a list
        if (ctx.exp() != null) {
            for (SimpleLangParser.ExpContext expContext : ctx.exp()) {
                returnValue = visit(expContext); // Visit each expression in the list
            }
        }

        return returnValue; // Return the result of the last expression
    }
    @Override
    public Integer visitBlock(SimpleLangParser.BlockContext ctx) {
        Integer returnValue = null;
        for (SimpleLangParser.ExpContext exp : ctx.ene().exp()) {
            returnValue = visit(exp);
        }
        return returnValue;
    }

    @Override
    public Integer visitAssignExpr(SimpleLangParser.AssignExprContext ctx) {
        Integer value = visit(ctx.exp());
        String varName = ctx.Idfr().getText();
        frames.peek().put(varName, value); // Update the variable in the current frame
        return value;
    }

    @Override public Integer visitBinOpExpr(SimpleLangParser.BinOpExprContext ctx) {

        SimpleLangParser.ExpContext operand1 = ctx.exp(0);
        Integer oprnd1 = visit(operand1);
        SimpleLangParser.ExpContext operand2 = ctx.exp(1);
        Integer oprnd2 = visit(operand2);

        switch (((TerminalNode) (ctx.binop().getChild(0))).getSymbol().getType()) {
            case SimpleLangParser.Eq ->  {
                return ((Objects.equals(oprnd1, oprnd2)) ? 1 : 0);
            }
            case SimpleLangParser.Less -> {
                return ((oprnd1 < oprnd2) ? 1 : 0);
            }
            case SimpleLangParser.LessEq -> {
                return ((oprnd1 <= oprnd2) ? 1 : 0);
            }
            case SimpleLangParser.Plus -> {
                return oprnd1 + oprnd2;
            }
            case SimpleLangParser.Minus -> {
                return oprnd1 - oprnd2;
            }
            case SimpleLangParser.Times -> {
                return oprnd1 * oprnd2;
            }
            case SimpleLangParser.And -> {
                return (oprnd1 != 0 && oprnd2 != 0) ? 1 : 0;
            }
            case SimpleLangParser.Or -> {
                return (oprnd1 != 0 || oprnd2 != 0) ? 1 : 0;
            }
            case SimpleLangParser.Xor -> {
                return ((oprnd1 != 0) ^ (oprnd2 != 0)) ? 1 : 0;
            }
            default -> {
                throw new RuntimeException("Shouldn't be here - wrong binary operator.");
            }
        }

    }
    @Override
    public Integer visitInvokeExpr(SimpleLangParser.InvokeExprContext ctx) {
        // Look up the function by name
        FunctionDetails funcDetails = global_funcs.get(ctx.Idfr().getText());
        if (funcDetails == null) {
            throw new RuntimeException("Undefined function: " + ctx.Idfr().getText());
        }

        // Create a new frame for the function call
        Map<String, Integer> newFrame = new HashMap<>();
        for (int i = 0; i < ctx.args.size(); i++) {
            String paramName = funcDetails.params.get(i).Idfr().getText();
            int argValue = visit(ctx.args.get(i)); // Evaluate argument expression
            newFrame.put(paramName, argValue);
        }

        frames.push(newFrame); // Push the new frame onto the stack
        Integer returnValue = visit(funcDetails.body); // Execute the function body
        frames.pop(); // Pop the frame after execution
        return returnValue;
    }

    @Override public Integer visitBlockExpr(SimpleLangParser.BlockExprContext ctx) {
        return visit(ctx.block());
    }

    @Override public Integer visitIfExpr(SimpleLangParser.IfExprContext ctx)
    {

        SimpleLangParser.ExpContext cond = ctx.exp();
        Integer condValue = visit(cond);
        if (condValue > 0) {

            SimpleLangParser.BlockContext thenBlock = ctx.block(0);
            return visit(thenBlock);

        } else {

            SimpleLangParser.BlockContext elseBlock = ctx.block(1);
            return visit(elseBlock);

        }

    }

    @Override
    public Integer visitWhileExpr(SimpleLangParser.WhileExprContext ctx) {
        Integer condValue = visit(ctx.exp());
        while (condValue > 0) { // Check if condition is true
            visit(ctx.block()); // Execute the block
            condValue = visit(ctx.exp()); // Reevaluate the condition
        }
        return null;
    }

    @Override
    public Integer visitRepeatExpr(SimpleLangParser.RepeatExprContext ctx) {
        do {
            visit(ctx.block());
        } while (visit(ctx.exp()) == 0);
        return null;
    }

    @Override public Integer visitPrintExpr(SimpleLangParser.PrintExprContext ctx) {

        SimpleLangParser.ExpContext exp = ctx.exp();

        if (((TerminalNode) exp.getChild(0)).getSymbol().getType() == SimpleLangParser.Space) {

            System.out.print(" ");

        } else if (((TerminalNode) exp.getChild(0)).getSymbol().getType() == SimpleLangParser.NewLine) {

            System.out.println();

        } else {

            System.out.print(visit(exp));

        }

        return null;

    }

    @Override
    public Integer visitSpaceExpr(SimpleLangParser.SpaceExprContext ctx) {
        System.out.print(" ");
        return null;
    }

    @Override
    public Integer visitNewLineExpr(SimpleLangParser.NewLineExprContext ctx) {
        System.out.println();
        return null;
    }

    @Override
    public Integer visitSkipExpr(SimpleLangParser.SkipExprContext ctx) {
        return 0; // Skip is effectively a no-op
    }

    @Override
    public Integer visitBoolExpr(SimpleLangParser.BoolExprContext ctx) {
        return ctx.getText().equals("true") ? 1 : 0;
    }

    @Override
    public Integer visitIdExpr(SimpleLangParser.IdExprContext ctx) {
        String varName = ctx.Idfr().getText();
        if (!frames.peek().containsKey(varName)) {
            throw new RuntimeException("Undefined variable: " + varName);
        }
        return frames.peek().get(varName);
    }

    @Override public Integer visitIntExpr(SimpleLangParser.IntExprContext ctx)
    {

        return Integer.parseInt(ctx.IntLit().getText());

    }
    @Override public Integer visitEqBinop(SimpleLangParser.EqBinopContext ctx) {
        throw new RuntimeException("Should not be here!");
    }
    @Override public Integer visitLessBinop(SimpleLangParser.LessBinopContext ctx) {
        throw new RuntimeException("Should not be here!");
    }
    @Override public Integer visitLessEqBinop(SimpleLangParser.LessEqBinopContext ctx) {
        throw new RuntimeException("Should not be here!");
    }
    @Override public Integer visitPlusBinop(SimpleLangParser.PlusBinopContext ctx) {
        throw new RuntimeException("Should not be here!");
    }
    @Override public Integer visitMinusBinop(SimpleLangParser.MinusBinopContext ctx) {
        throw new RuntimeException("Should not be here!");
    }
    @Override public Integer visitTimesBinop(SimpleLangParser.TimesBinopContext ctx) {
        throw new RuntimeException("Should not be here!");
    }

    @Override
    public Integer visitAndBinop(SimpleLangParser.AndBinopContext ctx) {
        throw new RuntimeException("Should not be here!");
    }
    @Override
    public Integer visitOrBinop(SimpleLangParser.OrBinopContext ctx) {
        throw new RuntimeException("Should not be here!");
    }

    @Override
    public Integer visitXorBinop(SimpleLangParser.XorBinopContext ctx) {
        throw new RuntimeException("Should not be here!");
    }

}
