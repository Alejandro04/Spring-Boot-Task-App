package tasks.tasksapp.mapper;

public interface IMapper <I, O>{
    public O map(I in);
}