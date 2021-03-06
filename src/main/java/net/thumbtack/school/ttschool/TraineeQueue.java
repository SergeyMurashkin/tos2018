package net.thumbtack.school.ttschool;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class TraineeQueue {

    private Queue<Trainee> traineeQueue;

    public TraineeQueue() {
        traineeQueue = new LinkedList<>();
    }

    public void addTrainee(Trainee trainee) {
        traineeQueue.add(trainee);
    }

    public Trainee removeTrainee() throws TrainingException {
        try {
            Trainee first = traineeQueue.element();
            traineeQueue.remove();
            return first;
        } catch (NoSuchElementException ex) {
            throw new TrainingException(TrainingErrorCode.EMPTY_TRAINEE_QUEUE);
        }
    }

    public boolean isEmpty() {
        return traineeQueue.isEmpty();
    }
}
