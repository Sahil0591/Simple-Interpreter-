// Generated from D:/Sahil/Intelli/Interpreter/279060/task1/src/SimpleLang.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpleLangParser}.
 */
public interface SimpleLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(SimpleLangParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(SimpleLangParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterDec(SimpleLangParser.DecContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitDec(SimpleLangParser.DecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#typed_idfr}.
	 * @param ctx the parse tree
	 */
	void enterTyped_idfr(SimpleLangParser.Typed_idfrContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#typed_idfr}.
	 * @param ctx the parse tree
	 */
	void exitTyped_idfr(SimpleLangParser.Typed_idfrContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(SimpleLangParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(SimpleLangParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(SimpleLangParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(SimpleLangParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLangParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(SimpleLangParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLangParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(SimpleLangParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(SimpleLangParser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(SimpleLangParser.AssignExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinOpExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterBinOpExpr(SimpleLangParser.BinOpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinOpExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitBinOpExpr(SimpleLangParser.BinOpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InvokeExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterInvokeExpr(SimpleLangParser.InvokeExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InvokeExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitInvokeExpr(SimpleLangParser.InvokeExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterBlockExpr(SimpleLangParser.BlockExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitBlockExpr(SimpleLangParser.BlockExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIfExpr(SimpleLangParser.IfExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIfExpr(SimpleLangParser.IfExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpr(SimpleLangParser.PrintExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpr(SimpleLangParser.PrintExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SpaceExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterSpaceExpr(SimpleLangParser.SpaceExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SpaceExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitSpaceExpr(SimpleLangParser.SpaceExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(SimpleLangParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(SimpleLangParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterIntExpr(SimpleLangParser.IntExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link SimpleLangParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitIntExpr(SimpleLangParser.IntExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqBinop}
	 * labeled alternative in {@link SimpleLangParser#binop}.
	 * @param ctx the parse tree
	 */
	void enterEqBinop(SimpleLangParser.EqBinopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqBinop}
	 * labeled alternative in {@link SimpleLangParser#binop}.
	 * @param ctx the parse tree
	 */
	void exitEqBinop(SimpleLangParser.EqBinopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LessBinop}
	 * labeled alternative in {@link SimpleLangParser#binop}.
	 * @param ctx the parse tree
	 */
	void enterLessBinop(SimpleLangParser.LessBinopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LessBinop}
	 * labeled alternative in {@link SimpleLangParser#binop}.
	 * @param ctx the parse tree
	 */
	void exitLessBinop(SimpleLangParser.LessBinopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LessEqBinop}
	 * labeled alternative in {@link SimpleLangParser#binop}.
	 * @param ctx the parse tree
	 */
	void enterLessEqBinop(SimpleLangParser.LessEqBinopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LessEqBinop}
	 * labeled alternative in {@link SimpleLangParser#binop}.
	 * @param ctx the parse tree
	 */
	void exitLessEqBinop(SimpleLangParser.LessEqBinopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PlusBinop}
	 * labeled alternative in {@link SimpleLangParser#binop}.
	 * @param ctx the parse tree
	 */
	void enterPlusBinop(SimpleLangParser.PlusBinopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PlusBinop}
	 * labeled alternative in {@link SimpleLangParser#binop}.
	 * @param ctx the parse tree
	 */
	void exitPlusBinop(SimpleLangParser.PlusBinopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MinusBinop}
	 * labeled alternative in {@link SimpleLangParser#binop}.
	 * @param ctx the parse tree
	 */
	void enterMinusBinop(SimpleLangParser.MinusBinopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MinusBinop}
	 * labeled alternative in {@link SimpleLangParser#binop}.
	 * @param ctx the parse tree
	 */
	void exitMinusBinop(SimpleLangParser.MinusBinopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TimesBinop}
	 * labeled alternative in {@link SimpleLangParser#binop}.
	 * @param ctx the parse tree
	 */
	void enterTimesBinop(SimpleLangParser.TimesBinopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TimesBinop}
	 * labeled alternative in {@link SimpleLangParser#binop}.
	 * @param ctx the parse tree
	 */
	void exitTimesBinop(SimpleLangParser.TimesBinopContext ctx);
}