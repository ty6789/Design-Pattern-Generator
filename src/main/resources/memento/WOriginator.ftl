package 11package

// Originator
public class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    // Create a memento that saves the current state
    public Memento createMemento() {
        return new Memento(this.state);
    }

    // Restore the state from a memento
    public void restoreMemento(Memento memento) {
        this.setState(memento.getState());
    }
}