package UnOp;

/**
 *LogExpr est la classe qui s'occupe de la fonction logexponentielle pour le type Expression.
 *
 *@author Pierre Bracchi
 */


public class LogExpr implements Expression{
    private final Expression expr;

    public LogExpr(Expression expr){
	this.expr=expr;
    }
    /**
     *substitue une expression a la place d'une inconnue dans une autre expression
     *@param var est l'inconnue qui va etre substituée
             exp est l'expression qui va substituer var
     *@return une Expression de type ConstExpr ou LogExpr
     */

    @Override
    public Expression subst(String var,Expression exp){
	return LogExpr.Of(expr.subst(var,exp));
    }

    public static Expression Of(Expression op){
	if(op instanceof ConstExpr)
	    return new ConstExpr(Math.log(((ConstExpr)op).getVal()));
	else
	    return new  LogExpr(op);
    }

    @Override
    public String toString(){
	return "(log("+expr+"))";
    }
}
