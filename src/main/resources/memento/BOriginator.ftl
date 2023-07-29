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
    public MementoIF createMemento() {
        return new Memento(this.state);
    }

    // Restore the state from a memento
    public void restoreMemento(MementoIF memento) {
        this.setState(((Memento) memento).getState());
    }

    // Private inner Memento class
    private class Memento implements MementoIF {

        private String state;

        private Memento(String state) {
            this.state = state;
        }

        private String getState() {
            return state;
        }

        private void setState(String state) {
            this.state = state;
        }
    }

}