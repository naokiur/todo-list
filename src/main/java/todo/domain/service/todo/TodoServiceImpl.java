package todo.domain.service.todo;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import todo.domain.model.Todo;
import todo.domain.repository.todo.TodoRepository;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    private static final long MAX_UNFINISHED_COUNT = 5;

    // Because this repository has only 1 constructor, Annotation is not need ?
    // https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-spring-beans-and-dependency-injection.html
    // -> This is wrong. If Developer does not want to write annotation, Developer need to write constructor.
    TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public Todo findOne(String todoId) {
        Todo todo = todoRepository.findOne(todoId);

        if (todo == null) {
            // TODO Need to build Exception structure.
//            throw new ResourceNotFoundException("");

        }

        return todo;
    }

    @Override
    @Transactional(readOnly=true)
    public Collection<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Todo create(Todo todo) {
        long unfinishedCount = todoRepository.countByFinished(false);

        if (unfinishedCount >= MAX_UNFINISHED_COUNT) {
            // TODO Exception
        }

        String todoId = UUID.randomUUID().toString();
        Date createdAt = new Date();

        todo.setTodoId(todoId);
        todo.setCreatedAt(createdAt);
        todo.setFinished(false);

        todoRepository.create(todo);

        return todo;
    }

    @Override
    public Todo finish(String todoId) {
        Todo todo = todoRepository.findOne(todoId);
        if (todo.isFinished()) {
            // TODO Exception

        }

        todo.setFinished(true);
        todoRepository.update(todo);

        return todo;
    }

    @Override
    public void delete(String todoId) {
        Todo todo = todoRepository.findOne(todoId);
        todoRepository.delete(todo);
    }
}
