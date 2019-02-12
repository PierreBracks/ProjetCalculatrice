package UnOp;

/**
 *SinExpr est la classe qui s'occupe de l'operation sin pour le type Expression.
 *
 *@author Pierre Bracchi
 */


public class SinExpr implements Expression{
    private final Expression expr;

    public SinExpr(Expression expr){
	this.expr=expr;
    }

        /**
     *substitue une expression a la place d'une inconnue dans une autre expression
     *@param var est l'inconnue qui va etre substituée
             exp est l'expression qui va substituer var
     *@return une Expression de type ConstExpr ou SinExpr
     */

    @Override
    public Expression subst(String var, Expression exp){
	return SinExpr.Of(expr.subst(var,exp));
    }

    public static Expression Of(Expression op){
	if (op instanceof ConstExpr)
	    return new ConstExpr(Math.sin(((ConstExpr)op).getVal()));
	else
	    return new SinExpr(op);
    }

    @Override
    public String toString(){
	return "(sin("+expr+"))";
    }
}
