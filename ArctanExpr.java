package UnOp;

/**
 *ArctanExpr est la classe qui s'occupe de l'operation Arctan pour le type Expression.
 *
 *@author Pierre Bracchi
 */




public class ArctanExpr implements Expression{
    private final Expression expr;

    public ArctanExpr(Expression expr){
	this.expr=expr;
    }

        /**
     *substitue une expression a la place d'une inconnue dans une autre expression
     *@param var est l'inconnue qui va etre substituée
             exp est l'expression qui va substituer var
     *@return une Expression de type ConstExpr ou ArctanExpr
     */

    @Override
    public Expression subst(String var,Expression exp){
	return ArctanExpr.Of(expr.subst(var,exp));
    }

    public static Expression Of(Expression op){
	if (op instanceof ConstExpr)
	    return new ConstExpr(Math.atan(((ConstExpr)op).getVal()));
	else
	    return new ArctanExpr(op);
    }
    
    @Override
    public String toString(){
	return "(Arctan("+expr+"))";
    }
}
