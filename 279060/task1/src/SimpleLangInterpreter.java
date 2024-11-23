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
        System.out.println("Arguments passed to program: " + Arrays.toString(args));

        for (SimpleLangParser.DecContext dec : ctx.dec()) {
            String functionName = dec.typed_idfr(0).Idfr().getText();
            FunctionDetails funcDetails = new FunctionDetails(
                    functionName,
                    dec.typed_idfr().subList(1, dec.typed_idfr().size()),
                    dec.body()
            );
            global_funcs.put(functionName, funcDetails);
        }
        FunctionDetails mainFunction = global_funcs.get("main");
        System.out.println("Main function details: " + mainFunction.name + ", parameters: " + mainFunction.params);

        Map<String, Integer> newFrame = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            String paramName = mainFunction.params.get(i).Idfr().getText();
            int value = args[i].equals("true") ? 1 : args[i].equals("false") ? 0 : Integer.parseInt(args[i]);
            newFrame.put(paramName, value);
            System.out.println("Bound parameter " + paramName + " to value " + value);
        }

        frames.push(newFrame);
        System.out.println("Frame pushed: " + newFrame);
        Integer result = visit(mainFunction.body);
        System.out.println("Final result of program: " + result);

        return result;
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
            System.out.println("Initialized variable " + varName + " to 0");
        }

        Integer returnValue = visit(ctx.ene());
        System.out.println("Body execution returned: " + returnValue);

        return returnValue;
    }

    @Override
    public Integer visitEne(SimpleLangParser.EneContext ctx) {
        // Check if the context is null
        if (ctx == null) {
            System.err.println("Error: EneContext is null");
            return null; // Or throw an exception
        }

        // Check if the context contains any expressions
        if (ctx.exp() == null || ctx.exp().isEmpty()) {
            System.err.println("Warning: EneContext has no expressions");
            return null;
        }

        Integer returnValue = null;

        // Visit each expression in the context
        for (SimpleLangParser.ExpContext expContext : ctx.exp()) {
            returnValue = visit(expContext);
        }

        return returnValue;
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

    @Override
    public Integer visitBinOpExpr(SimpleLangParser.BinOpExprContext ctx) {
        // Evaluate operands
        Integer oprnd1 = visit(ctx.exp(0));
        Integer oprnd2 = visit(ctx.exp(1));

        // Log operand values and operator
        System.out.println("Binary operation: " + ctx.binop().getText());
        System.out.println("Operand 1: " + oprnd1 + ", Operand 2: " + oprnd2);

        // Null safety check
        if (oprnd1 == null || oprnd2 == null) {
            throw new RuntimeException("Binary operation with null operand.");
        }

        // Determine the result based on the operator
        Integer result;
        switch (((TerminalNode) ctx.binop().getChild(0)).getSymbol().getType()) {
            case SimpleLangParser.Plus -> {
                result = oprnd1 + oprnd2;
                System.out.println("Performed addition. Result: " + result);
            }
            case SimpleLangParser.Minus -> {
                result = oprnd1 - oprnd2;
                System.out.println("Performed subtraction. Result: " + result);
            }
            case SimpleLangParser.Times -> {
                result = oprnd1 * oprnd2;
                System.out.println("Performed multiplication. Result: " + result);
            }
            case SimpleLangParser.Divide -> {
                if (oprnd2 == 0) {
                    throw new RuntimeException("Division by zero.");
                }
                result = oprnd1 / oprnd2;
                System.out.println("Performed division. Result: " + result);
            }
            case SimpleLangParser.Eq -> {
                result = oprnd1.equals(oprnd2) ? 1 : 0;
                System.out.println("Performed equality check. Result: " + result);
            }
            case SimpleLangParser.Less -> {
                result = (oprnd1 < oprnd2) ? 1 : 0;
                System.out.println("Performed less-than check. Result: " + result);
            }
            case SimpleLangParser.Great -> {
                result = (oprnd1 > oprnd2) ? 1 : 0;
                System.out.println("Performed greater-than check. Result: " + result);
            }
            case SimpleLangParser.LessEq -> {
                result = (oprnd1 <= oprnd2) ? 1 : 0;
                System.out.println("Performed less-than-or-equal-to check. Result: " + result);
            }
            case SimpleLangParser.GreatEq -> {
                result = (oprnd1 >= oprnd2) ? 1 : 0;
                System.out.println("Performed greater-than-or-equal-to check. Result: " + result);
            }
            case SimpleLangParser.And -> {
                result = (oprnd1 & oprnd2);
                System.out.println("Performed logical AND. Result: " + result);
            }
            case SimpleLangParser.Or -> {
                result = (oprnd1 | oprnd2);
                System.out.println("Performed logical OR. Result: " + result);
            }
            default -> throw new RuntimeException("Unsupported binary operator: " + ctx.binop().getText());
        }

        // Log the final result of the binary operation
        System.out.println("Binary operation result: " + result);
        return result;
    }


    @Override
    public Integer visitInvokeExpr(SimpleLangParser.InvokeExprContext ctx) {
        String functionName = ctx.Idfr().getText();
        FunctionDetails funcDetails = global_funcs.get(functionName);
        if (funcDetails == null) {
            throw new RuntimeException("Undefined function: " + functionName);
        }

        // Debug: Verify context alignment
        System.out.println("Invoking function: " + functionName);
        System.out.println("Function parameters: " + funcDetails.params);
        System.out.println("Arguments in ctx: " + ctx.args);

        // Create a new frame for the function
        Map<String, Integer> newFrame = new HashMap<>();
        for (int i = 0; i < ctx.args.size(); i++) {
            String paramName = funcDetails.params.get(i).Idfr().getText();
            Integer argValue = visit(ctx.args.get(i)); // Evaluate arguments
            if (argValue == null) {
                throw new RuntimeException("Argument " + ctx.args.get(i).getText() + " evaluated to null.");
            }
            newFrame.put(paramName, argValue);
            System.out.println("Mapped parameter " + paramName + " to value " + argValue);
        }

        // Push the frame and execute the function body
        frames.push(newFrame);
        System.out.println("Frame before function execution: " + frames);

        Integer returnValue;
        try {
            returnValue = visit(funcDetails.body);
        } finally {
            frames.pop(); // Ensure proper cleanup of the frame
        }

        System.out.println("Function " + functionName + " returned: " + returnValue);
        return returnValue;
    }


    @Override public Integer visitBlockExpr(SimpleLangParser.BlockExprContext ctx) {
        return visit(ctx.block());
    }

    @Override
    public Integer visitIfExpr(SimpleLangParser.IfExprContext ctx) {
        Integer condValue = visit(ctx.exp());
        System.out.println("Condition evaluated to: " + condValue);

        if (condValue > 0) {
            System.out.println("Executing THEN branch");
            return visit(ctx.block(0));
        } else {
            System.out.println("Executing ELSE branch");
            return visit(ctx.block(1));
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
        System.out.println("Attempting to resolve variable: " + varName);

        // Check current frame for the variable
        if (!frames.peek().containsKey(varName)) {
            throw new RuntimeException("Undefined variable: " + varName + " in current frame.");
        }

        Integer value = frames.peek().get(varName);
        System.out.println("Resolved variable " + varName + " to value: " + value);
        return value;
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

    @Override
    public Integer visitGreatBinop(SimpleLangParser.GreatBinopContext ctx) {
        throw new RuntimeException("Should not be here!");
    }

    @Override
    public Integer visitGreatEqBinop(SimpleLangParser.GreatEqBinopContext ctx) {
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
    public Integer visitDivideBinop(SimpleLangParser.DivideBinopContext ctx) {
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
