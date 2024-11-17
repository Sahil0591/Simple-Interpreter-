// Generated from C:/Users/user/Dropbox/_Teaching/Compilers/coursework/123456/task1/src/SimpleLang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class SimpleLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LParen=1, Comma=2, RParen=3, LBrace=4, Semicolon=5, RBrace=6, Eq=7, Less=8, 
		LessEq=9, Plus=10, Times=11, Minus=12, Assign=13, Print=14, Space=15, 
		NewLine=16, If=17, Then=18, Else=19, IntType=20, BoolType=21, UnitType=22, 
		BoolLit=23, IntLit=24, Idfr=25, WS=26;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"LParen", "Comma", "RParen", "LBrace", "Semicolon", "RBrace", "Eq", "Less", 
			"LessEq", "Plus", "Times", "Minus", "Assign", "Print", "Space", "NewLine", 
			"If", "Then", "Else", "IntType", "BoolType", "UnitType", "BoolLit", "IntLit", 
			"Idfr", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "','", "')'", "'{'", "';'", "'}'", "'=='", "'<'", "'<='", 
			"'+'", "'*'", "'-'", "':='", "'print'", "'space'", "'newline'", "'if'", 
			"'then'", "'else'", "'int'", "'bool'", "'unit'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LParen", "Comma", "RParen", "LBrace", "Semicolon", "RBrace", "Eq", 
			"Less", "LessEq", "Plus", "Times", "Minus", "Assign", "Print", "Space", 
			"NewLine", "If", "Then", "Else", "IntType", "BoolType", "UnitType", "BoolLit", 
			"IntLit", "Idfr", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public SimpleLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SimpleLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001a\u00a7\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003"+
		"\u0016\u008b\b\u0016\u0001\u0017\u0001\u0017\u0003\u0017\u008f\b\u0017"+
		"\u0001\u0017\u0001\u0017\u0005\u0017\u0093\b\u0017\n\u0017\f\u0017\u0096"+
		"\t\u0017\u0003\u0017\u0098\b\u0017\u0001\u0018\u0001\u0018\u0005\u0018"+
		"\u009c\b\u0018\n\u0018\f\u0018\u009f\t\u0018\u0001\u0019\u0004\u0019\u00a2"+
		"\b\u0019\u000b\u0019\f\u0019\u00a3\u0001\u0019\u0001\u0019\u0000\u0000"+
		"\u001a\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006"+
		"\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e"+
		"\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017"+
		"/\u00181\u00193\u001a\u0001\u0000\u0005\u0001\u000019\u0001\u000009\u0001"+
		"\u0000az\u0004\u000009AZ__az\u0003\u0000\t\n\r\r  \u00ac\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000"+
		"\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000"+
		"\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000"+
		"\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000"+
		"\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000"+
		"\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'"+
		"\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000"+
		"\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000"+
		"\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00015"+
		"\u0001\u0000\u0000\u0000\u00037\u0001\u0000\u0000\u0000\u00059\u0001\u0000"+
		"\u0000\u0000\u0007;\u0001\u0000\u0000\u0000\t=\u0001\u0000\u0000\u0000"+
		"\u000b?\u0001\u0000\u0000\u0000\rA\u0001\u0000\u0000\u0000\u000fD\u0001"+
		"\u0000\u0000\u0000\u0011F\u0001\u0000\u0000\u0000\u0013I\u0001\u0000\u0000"+
		"\u0000\u0015K\u0001\u0000\u0000\u0000\u0017M\u0001\u0000\u0000\u0000\u0019"+
		"O\u0001\u0000\u0000\u0000\u001bR\u0001\u0000\u0000\u0000\u001dX\u0001"+
		"\u0000\u0000\u0000\u001f^\u0001\u0000\u0000\u0000!f\u0001\u0000\u0000"+
		"\u0000#i\u0001\u0000\u0000\u0000%n\u0001\u0000\u0000\u0000\'s\u0001\u0000"+
		"\u0000\u0000)w\u0001\u0000\u0000\u0000+|\u0001\u0000\u0000\u0000-\u008a"+
		"\u0001\u0000\u0000\u0000/\u0097\u0001\u0000\u0000\u00001\u0099\u0001\u0000"+
		"\u0000\u00003\u00a1\u0001\u0000\u0000\u000056\u0005(\u0000\u00006\u0002"+
		"\u0001\u0000\u0000\u000078\u0005,\u0000\u00008\u0004\u0001\u0000\u0000"+
		"\u00009:\u0005)\u0000\u0000:\u0006\u0001\u0000\u0000\u0000;<\u0005{\u0000"+
		"\u0000<\b\u0001\u0000\u0000\u0000=>\u0005;\u0000\u0000>\n\u0001\u0000"+
		"\u0000\u0000?@\u0005}\u0000\u0000@\f\u0001\u0000\u0000\u0000AB\u0005="+
		"\u0000\u0000BC\u0005=\u0000\u0000C\u000e\u0001\u0000\u0000\u0000DE\u0005"+
		"<\u0000\u0000E\u0010\u0001\u0000\u0000\u0000FG\u0005<\u0000\u0000GH\u0005"+
		"=\u0000\u0000H\u0012\u0001\u0000\u0000\u0000IJ\u0005+\u0000\u0000J\u0014"+
		"\u0001\u0000\u0000\u0000KL\u0005*\u0000\u0000L\u0016\u0001\u0000\u0000"+
		"\u0000MN\u0005-\u0000\u0000N\u0018\u0001\u0000\u0000\u0000OP\u0005:\u0000"+
		"\u0000PQ\u0005=\u0000\u0000Q\u001a\u0001\u0000\u0000\u0000RS\u0005p\u0000"+
		"\u0000ST\u0005r\u0000\u0000TU\u0005i\u0000\u0000UV\u0005n\u0000\u0000"+
		"VW\u0005t\u0000\u0000W\u001c\u0001\u0000\u0000\u0000XY\u0005s\u0000\u0000"+
		"YZ\u0005p\u0000\u0000Z[\u0005a\u0000\u0000[\\\u0005c\u0000\u0000\\]\u0005"+
		"e\u0000\u0000]\u001e\u0001\u0000\u0000\u0000^_\u0005n\u0000\u0000_`\u0005"+
		"e\u0000\u0000`a\u0005w\u0000\u0000ab\u0005l\u0000\u0000bc\u0005i\u0000"+
		"\u0000cd\u0005n\u0000\u0000de\u0005e\u0000\u0000e \u0001\u0000\u0000\u0000"+
		"fg\u0005i\u0000\u0000gh\u0005f\u0000\u0000h\"\u0001\u0000\u0000\u0000"+
		"ij\u0005t\u0000\u0000jk\u0005h\u0000\u0000kl\u0005e\u0000\u0000lm\u0005"+
		"n\u0000\u0000m$\u0001\u0000\u0000\u0000no\u0005e\u0000\u0000op\u0005l"+
		"\u0000\u0000pq\u0005s\u0000\u0000qr\u0005e\u0000\u0000r&\u0001\u0000\u0000"+
		"\u0000st\u0005i\u0000\u0000tu\u0005n\u0000\u0000uv\u0005t\u0000\u0000"+
		"v(\u0001\u0000\u0000\u0000wx\u0005b\u0000\u0000xy\u0005o\u0000\u0000y"+
		"z\u0005o\u0000\u0000z{\u0005l\u0000\u0000{*\u0001\u0000\u0000\u0000|}"+
		"\u0005u\u0000\u0000}~\u0005n\u0000\u0000~\u007f\u0005i\u0000\u0000\u007f"+
		"\u0080\u0005t\u0000\u0000\u0080,\u0001\u0000\u0000\u0000\u0081\u0082\u0005"+
		"t\u0000\u0000\u0082\u0083\u0005r\u0000\u0000\u0083\u0084\u0005u\u0000"+
		"\u0000\u0084\u008b\u0005e\u0000\u0000\u0085\u0086\u0005f\u0000\u0000\u0086"+
		"\u0087\u0005a\u0000\u0000\u0087\u0088\u0005l\u0000\u0000\u0088\u0089\u0005"+
		"s\u0000\u0000\u0089\u008b\u0005e\u0000\u0000\u008a\u0081\u0001\u0000\u0000"+
		"\u0000\u008a\u0085\u0001\u0000\u0000\u0000\u008b.\u0001\u0000\u0000\u0000"+
		"\u008c\u0098\u00050\u0000\u0000\u008d\u008f\u0005-\u0000\u0000\u008e\u008d"+
		"\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0090"+
		"\u0001\u0000\u0000\u0000\u0090\u0094\u0007\u0000\u0000\u0000\u0091\u0093"+
		"\u0007\u0001\u0000\u0000\u0092\u0091\u0001\u0000\u0000\u0000\u0093\u0096"+
		"\u0001\u0000\u0000\u0000\u0094\u0092\u0001\u0000\u0000\u0000\u0094\u0095"+
		"\u0001\u0000\u0000\u0000\u0095\u0098\u0001\u0000\u0000\u0000\u0096\u0094"+
		"\u0001\u0000\u0000\u0000\u0097\u008c\u0001\u0000\u0000\u0000\u0097\u008e"+
		"\u0001\u0000\u0000\u0000\u00980\u0001\u0000\u0000\u0000\u0099\u009d\u0007"+
		"\u0002\u0000\u0000\u009a\u009c\u0007\u0003\u0000\u0000\u009b\u009a\u0001"+
		"\u0000\u0000\u0000\u009c\u009f\u0001\u0000\u0000\u0000\u009d\u009b\u0001"+
		"\u0000\u0000\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e2\u0001\u0000"+
		"\u0000\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u00a0\u00a2\u0007\u0004"+
		"\u0000\u0000\u00a1\u00a0\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000"+
		"\u0000\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a6\u0006\u0019"+
		"\u0000\u0000\u00a64\u0001\u0000\u0000\u0000\u0007\u0000\u008a\u008e\u0094"+
		"\u0097\u009d\u00a3\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}