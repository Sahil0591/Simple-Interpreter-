grammar SimpleLang;

prog : dec+ EOF;

dec
    : typed_idfr LParen (vardec+=typed_idfr)? RParen body
;

typed_idfr
    : type Idfr
;

type
    : IntType | BoolType | UnitType
;

body
    : LBrace ene+=exp (Semicolon ene+=exp)* RBrace
;

block
    : LBrace ene+=exp (Semicolon ene+=exp)* RBrace
;

exp
    : Idfr Assign exp                                       #AssignExpr
    | LParen exp binop exp RParen                           #BinOpExpr
    | Idfr LParen (args+=exp (Comma args+=exp)*)? RParen    #InvokeExpr
    | block                                                 #BlockExpr
    | If exp Then block Else block                          #IfExpr
    | Print exp                                             #PrintExpr
    | Space                                                 #SpaceExpr
    | Idfr                                                  #IdExpr
    | IntLit                                                #IntExpr
;

binop
    : Eq              #EqBinop
    | Less            #LessBinop
    | LessEq          #LessEqBinop
    | Plus            #PlusBinop
    | Minus           #MinusBinop
    | Times           #TimesBinop
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

Plus : '+' ;
Times : '*' ;
Minus : '-' ;

Assign : ':=' ;

Print : 'print' ;
Space : 'space' ;
NewLine : 'newline' ;
If : 'if' ;
Then : 'then' ;
Else : 'else' ;

IntType : 'int' ;
BoolType : 'bool' ;
UnitType : 'unit' ;

BoolLit : 'true' | 'false' ;
IntLit : '0' | ('-'? [1-9][0-9]*) ;
Idfr : [a-z][A-Za-z0-9_]* ;
WS : [ \n\r\t]+ -> skip ;