package cse_machine;

import cse_machine.elements.control.IdentifierControlElement;
import cse_machine.elements.control.GammaControlElement;
import cse_machine.elements.control.LambdaControlElement;

public interface ControlElementVisitor {
    public void visitGamma(GammaControlElement element);
    public void visitLambda(LambdaControlElement element);
    public void visitData(IdentifierControlElement element);
}
