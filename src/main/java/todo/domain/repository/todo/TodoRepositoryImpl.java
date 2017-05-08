package todo.domain.repository.todo;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import todo.domain.model.Todo;

@Repository
public class TodoRepositoryImpl implements TodoRepository {
    private static final Map<String, Todo> TODO_MAP = new ConcurrentHashMap<String, Todo>() {
        /** Default serialize version */
        private static final long serialVersionUID = 1L;

        {
            Todo defaultTodo = new Todo();
            defaultTodo.setTodoId("1");
            defaultTodo.setTodoTitle("Default Title");
            put(defaultTodo.getTodoId(), defaultTodo);
        };
    };

    @Override
    public Todo findOne(String todoId) {
        return TODO_MAP.get(todoId);
    }

    @Override
    public Collection<Todo> findAll() {
        return TODO_MAP.values();
    }

    @Override
    public void create(Todo todo) {
        TODO_MAP.put(todo.getTodoId(), todo);
    }

    @Override
    public boolean update(Todo todo) {
        TODO_MAP.put(todo.getTodoId(), todo);
        return true;
    }

    @Override
    public void delete(Todo todo) {
        TODO_MAP.remove(todo.getTodoId());
    }

    @Override
    public long countByFinished(boolean finished) {
        return TODO_MAP.values().stream().filter(t -> t.isFinished() == finished).count();
    }
}