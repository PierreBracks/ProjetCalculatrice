package UnOp;

/**
 *ArccosExpr est la classe qui s'occupe de l'operation Arccos pour le type Expression.
 *
 *@author Pierre Bracchi
 */


public class ArccosExpr implements Expression{
    private final Expression expr;

    public ArccosExpr(Expression expr){
	this.expr=expr;
    }
    /**
     *substitue une expression a la place d'une inconnue dans une autre expression
     *@param var est l'inconnue qui va etre substituée
             exp est l'expression qui va substituer var
     *@return une Expression de type ConstExpr ou ArccosExpr
     */

    @Override
    public Expression subst(String var,Expression exp){
	return ArccosExpr.Of(expr.subst(var,exp));
    }

    public static Expression Of(Expression op){
	if(op instanceof ConstExpr)
	    return new ConstExpr(Math.acos(((ConstExpr)op).getVal()));
	else
	    return new  ArccosExpr(op);
    }

    @Override
    public String toString(){
	return "(Arccos("+expr+"))";
    }
}
