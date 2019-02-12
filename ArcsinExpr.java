package UnOp;

/**
 *ArcsinExpr est la classe qui s'occupe de l'operation Arcsin pour le type Expression.
 *
 *@author Pierre Bracchi
 */



public class ArcsinExpr implements Expression{
    private final Expression expr;

    public ArcsinExpr(Expression expr){
	this.expr=expr;
    }

    /**
     *substitue une expression a la place d'une inconnue dans une autre expression
     *@param var est l'inconnue qui va etre substituée
             exp est l'expression qui va substituer var
     *@return une Expression de type ConstExpr ou ArcsinExpr
     */
    
    @Override
    public Expression subst(String var, Expression exp){
	return ArcsinExpr.Of(expr.subst(var,exp));
    }

    public static Expression Of(Expression op){
	if(op instanceof ConstExpr)
	    return new ConstExpr(Math.asin(((ConstExpr)op).getVal()));
	else
	    return new ArcsinExpr(op);
    }

    @Override
    public String toString(){
	return "(Arcsin("+expr+"))";
    }
}
