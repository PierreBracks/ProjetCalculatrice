package UnOp;

/**
 *ExpExpr est la classe qui s'occupe de la fonction exponentielle pour le type Expression.
 *
 *@author Pierre Bracchi
 */


public class ExpExpr implements Expression{
    private final Expression expr;

    public ExpExpr(Expression expr){
	this.expr=expr;
    }
    /**
     *substitue une expression a la place d'une inconnue dans une autre expression
     *@param var est l'inconnue qui va etre substituée
             exp est l'expression qui va substituer var
     *@return une Expression de type ConstExpr ou ExpExpr
     */

    @Override
    public Expression subst(String var,Expression exp){
	return ExpExpr.Of(expr.subst(var,exp));
    }

    public static Expression Of(Expression op){
	if(op instanceof ConstExpr)
	    return new ConstExpr(Math.exp(((ConstExpr)op).getVal()));
	else
	    return new  ExpExpr(op);
    }

    @Override
    public String toString(){
	return "(Exp("+expr+"))";
    }
}
