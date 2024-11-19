import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

public class SimpleLangInterpreter extends AbstractParseTreeVisitor<Integer> implements SimpleLangVisitor<Integer> {

    private final Map<String, SimpleLangParser.DecContext> global_funcs = new HashMap<>();
    private final Stack<Map<String, Integer>> frames = new Stack<>();

    public Integer visitProgram(SimpleLangParser.ProgContext ctx, String[] args)
    {

        for (int i = 0; i < ctx.dec().size(); ++i) {

            SimpleLangParser.DecContext dec = ctx.dec(i);
            SimpleLangParser.Typed_idfrContext typedIdfr = dec.typed_idfr(0);
            global_funcs.put(typedIdfr.Idfr().getText(), dec);

        }

        SimpleLangParser.DecContext main = global_funcs.get("main");

        Map<String, Integer> newFrame = new HashMap<>();
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("true")) {
                newFrame.put(main.typed_idfr().get(i).Idfr().getText(), 1);
            } else if (args[i].equals("false")) {
                newFrame.put(main.typed_idfr().get(i).Idfr().getText(), 0);
            } else {
                newFrame.put(main.typed_idfr().get(i).Idfr().getText(), Integer.parseInt(args[i]));
            }
        }

        frames.push(newFrame);
        return visit(main);

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
        Integer returnValue = null;

        // Iterate through variable declarations
        for (SimpleLangParser.Typed_idfrContext varDec : ctx.vardec) {
            String varName = varDec.Idfr().getText();
            frames.peek().put(varName, 0); // Initialize with a default value (e.g., 0)
        }

        // Visit the ene context
        if (ctx.ene() != null) {
            returnValue = visit(ctx.ene());
        }

        return returnValue;
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
    @Override public Integer visitBlock(SimpleLangParser.BlockContext ctx)
    {
        Integer returnValue = null;
        List<SimpleLangParser.ExpContext> exps = ctx.ene().exp();
        for (SimpleLangParser.ExpContext exp : exps) {
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
        SimpleLangParser.DecContext dec = global_funcs.get(ctx.Idfr().getText());
        Map<String, Integer> newFrame = new HashMap<>();

        // Add all arguments to the new frame
        for (int i = 0; i < ctx.args.size(); i++) {
            SimpleLangParser.Typed_idfrContext param = dec.vardec.get(i);
            SimpleLangParser.ExpContext arg = ctx.args.get(i);
            newFrame.put(param.Idfr().getText(), visit(arg));
        }

        frames.push(newFrame);
        return visit(dec);
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

    @Override public Integer visitIdExpr(SimpleLangParser.IdExprContext ctx)
    {
        return frames.peek().get(ctx.Idfr().getText());
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
