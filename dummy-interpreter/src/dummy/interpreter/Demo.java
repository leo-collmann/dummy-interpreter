package dummy.interpreter;

import java.util.ArrayList;
import java.util.List;

import dummy.structure.Statement;
import dummy.structure.impl.ANDExpression;
import dummy.structure.impl.BooleanExpression;
import dummy.structure.impl.IfStatement;
import dummy.structure.impl.Output;

public class Demo {
	public static void main(String[] args) {
		Program prog = createDemoProgram();
		Interpreter interpreter = new Interpreter();
		interpreter.interpret(prog);
	}
	/**
	 * Creates a "program" like
	 * 
	 * demo() {
	 *     if((true && true) && true) {
	 *         System.out.println("IfStatement was evaluated to TRUE and THEN is being processed...");
	 *     } else {
	 *     }
	 * }
	 *
	 */
	private static Program createDemoProgram() {
		BooleanExpression bTrue = new BooleanExpression(true);
		BooleanExpression bTrue2 = new BooleanExpression(true);
		ANDExpression innerAND = new ANDExpression(bTrue, bTrue2);
		ANDExpression outerAND = new ANDExpression(innerAND, bTrue);
		Output thenSuccess = new Output("IfStatement was evaluated to TRUE and THEN is being processed...");
		List<Statement> thenStatements = new ArrayList<Statement>();
		thenStatements.add(thenSuccess);
		IfStatement ifStatement = new IfStatement(outerAND, thenStatements, new ArrayList<Statement>());
		Program prog = new Program();
		prog.addStatement(ifStatement);
		return prog;
	}
}
