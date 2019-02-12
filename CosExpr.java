package UnOp;

/**
 *CosExpr est la classe qui s'occupe de l'operation cos pour le type Expression.
 *
 *@author Pierre Bracchi
 */


public class CosExpr implements Expression{
    private final Expression expr;

    public CosExpr(Expression expr){
	this.expr=expr;
    }
    /**
     *substitue une expression a la place d'une inconnue dans une autre expression
     *@param var est l'inconnue qui va etre substituée
             exp est l'expression qui va substituer var
     *@return une Expression de type ConstExpr ou ArccosExpr
     */

    
    @Override
    public Expression subst(String var, Expression exp){
	return CosExpr.Of(expr.subst(var,exp));
    }

    public static Expression Of(Expression op){
	if (op instanceof ConstExpr)
	    return new ConstExpr(Math.cos(((ConstExpr)op).getVal()));
	else
	    return new CosExpr(op);
    }

    @Override
    public String toString(){
	return "(cos("+expr+"))";
    }
}
