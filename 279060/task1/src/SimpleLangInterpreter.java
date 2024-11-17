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

    @Override public Integer visitBody(SimpleLangParser.BodyContext ctx) {

        Integer returnValue = null;
        for (int i = 0; i < ctx.ene.size(); ++i) {
            SimpleLangParser.ExpContext exp = ctx.ene.get(i);
            returnValue = visit(exp);
        }
        return returnValue;

    }

    @Override public Integer visitBlock(SimpleLangParser.BlockContext ctx)
    {
        Integer returnValue = null;
        for (int i = 0; i < ctx.ene.size(); ++i) {
            SimpleLangParser.ExpContext exp = ctx.ene.get(i);
            returnValue = visit(exp);
        }
        return returnValue;
    }

    @Override public Integer visitAssignExpr(SimpleLangParser.AssignExprContext ctx)
    {

        SimpleLangParser.ExpContext rhs = ctx.exp();
        frames.peek().replace(ctx.Idfr().getText(), visit(rhs));
        return null;

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
            default -> {
                throw new RuntimeException("Shouldn't be here - wrong binary operator.");
            }

        }

    }
    @Override public Integer visitInvokeExpr(SimpleLangParser.InvokeExprContext ctx)
    {

        SimpleLangParser.DecContext dec = global_funcs.get(ctx.Idfr().getText());
        SimpleLangParser.Typed_idfrContext param = dec.vardec.get(0);
        Map<String, Integer> newFrame = new HashMap<>();

        SimpleLangParser.ExpContext exp = ctx.args.get(0);
        newFrame.put(param.Idfr().getText(), visit(exp));

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

    @Override public Integer visitSpaceExpr(SimpleLangParser.SpaceExprContext ctx) {
        return null;
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

}
