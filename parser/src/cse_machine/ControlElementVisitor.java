package cse_machine;

import cse_machine.elements.IdentifierControlElement;
import cse_machine.elements.GammaControlElement;
import cse_machine.elements.LambdaControlElement;

public interface ControlElementVisitor {
    public void visitGamma(GammaControlElement element);
    public void visitLambda(LambdaControlElement element);
    public void visitData(IdentifierControlElement element);
}
