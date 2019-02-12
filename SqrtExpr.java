package UnOp;

/**
 *SqrtExpr est la classe qui s'occupe de la fonction racine pour le type Expression.
 *
 *@author Pierre Bracchi
 */


public class SqrtExpr implements Expression{
    private final Expression expr;

    public SqrtExpr(Expression expr){
	this.expr=expr;
    }
    /**
     *substitue une expression a la place d'une inconnue dans une autre expression
     *@param var est l'inconnue qui va etre substituée
             exp est l'expression qui va substituer var
     *@return une Expression de type ConstExpr ou SqrtExpr
     */

    @Override
    public Expression subst(String var,Expression exp){
	return SqrtExpr.Of(expr.subst(var,exp));
    }

    public static Expression Of(Expression op){
	if(op instanceof ConstExpr)
	    return new ConstExpr(Math.sqrt(((ConstExpr)op).getVal()));
	else
	    return new  SqrtExpr(op);
    }

    @Override
    public String toString(){
	return "(sqrt("+expr+"))";
    }
}
