package todo;

import java.io.Serializable;

public class TodoForm implements Serializable {

    /** Default serialize version */
    private static final long serialVersionUID = 1L;

    private String todoTitle;

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

}
