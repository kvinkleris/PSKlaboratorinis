package lt.vu.decorators;


import lt.vu.usecases.UpdateTreeNameInterface;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class UpdateTreeNameDecorator implements UpdateTreeNameInterface {
    @Inject
    @Delegate
    @Any
    UpdateTreeNameInterface updateTreeName;


    @Override
    public String updateNameOfTree() throws InterruptedException {
        String result = updateTreeName.updateNameOfTree();
        return result + "&decorated=yes";
    }
}