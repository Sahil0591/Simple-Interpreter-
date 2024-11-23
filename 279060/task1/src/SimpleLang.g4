grammar SimpleLang;

prog : dec+ EOF;

dec
    : typed_idfr LParen (vardec+=typed_idfr (Comma vardec+=typed_idfr)*)? RParen body
;

typed_idfr
    : type Idfr
;

type
    : IntType | BoolType | UnitType
;

block
    : LBrace ene RBrace;

body
    : LBrace (vardec+= init_expr Semicolon)* ene RBrace
;

ene
    : exp (Semicolon exp)*;

exp
    : Idfr Assign exp                                       #AssignExpr
    | LParen exp binop exp RParen                           #BinOpExpr
    | Idfr LParen (args+=exp (Comma args+=exp)*)? RParen    #InvokeExpr
    | block                                                 #BlockExpr
    | If exp Then block Else block                          #IfExpr
    | While exp Do block                                    #WhileExpr
    | Repeat block Until exp                                #RepeatExpr
    | Print exp                                             #PrintExpr
    | Space                                                 #SpaceExpr
    | NewLine                                               #NewLineExpr
    | Skip                                                  #SkipExpr
    | BoolLit                                               #BoolExpr
    | Idfr                                                  #IdExpr
    | IntLit                                                #IntExpr
;

init_expr
    : typed_idfr Assign exp                                 #InitExpr
;

binop
    : Eq              #EqBinop
    | Less            #LessBinop
    | LessEq          #LessEqBinop
    | Great           #GreatBinop
    | GreatEq         #GreatEqBinop
    | Plus            #PlusBinop
    | Minus           #MinusBinop
    | Times           #TimesBinop
    | Divide          #DivideBinop
    | And             #AndBinop
    | Or              #OrBinop
    | Xor             #XorBinop
;

LParen : '(' ;
Comma : ',' ;
RParen : ')' ;
LBrace : '{' ;
Semicolon : ';' ;
RBrace : '}' ;

Eq : '==' ;
Less : '<' ;
LessEq : '<=' ;
Great : '>' ;
GreatEq : '>=' ;

Plus : '+' ;
Times : '*' ;
Minus : '-' ;
Divide : '/' ;

And : '&';
Or : '|';
Xor : '^';

Assign : ':=' ;

Print : 'print' ;
Space : 'space' ;
NewLine : 'newline' ;
If : 'if' ;
Then : 'then' ;
Else : 'else' ;
While : 'while';
Do : 'do';
Repeat : 'repeat';
Until : 'until';
Skip : 'skip';

IntType : 'int' ;
BoolType : 'bool' ;
UnitType : 'unit' ;

BoolLit : 'true' | 'false' ;
IntLit : '0' | ('-'? [1-9][0-9]*) ;
Idfr : [a-z][A-Za-z0-9_]* ;
WS : [ \n\r\t]+ -> skip ;