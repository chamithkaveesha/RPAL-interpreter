package tree.transform;

import cse_machine.elements.ControlElement;

import java.util.ArrayList;
import java.util.List;

public class ControlStructureBuilderHelper {
    private final List<ControlStructure> controlStructures = new ArrayList<>();
    private int currentLevel = 0;

    public ControlStructureBuilderHelper() {
        controlStructures.add(new ControlStructure(currentLevel));
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public ControlStructure getCurrentControlStructure() {
        return controlStructures.get(currentLevel);
    }

    public List<ControlStructure> getAllControlStructures() {
        return controlStructures;
    }

    public void addControlElement(ControlElement element) {
        getCurrentControlStructure().addElement(element);
    }

    public void incrementLevel() {
        currentLevel++;
        controlStructures.add(new ControlStructure(currentLevel));
    }

    public ControlStructure getControlStructure(int level) {
        if (level < 0 || level >= controlStructures.size()) {
            throw new IllegalArgumentException("Invalid level");
        }
        return controlStructures.get(level);
    }
}
