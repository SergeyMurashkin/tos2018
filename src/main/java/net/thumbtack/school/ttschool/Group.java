package net.thumbtack.school.ttschool;

import java.util.*;

public class Group {

    private String name;
    private String room;
    private List<Trainee> trainees = new ArrayList<>();

    public Group(String name, String room) throws TrainingException {
        setName(name);
        setRoom(room);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TrainingException {
        if (name == null || name.trim().equals("")) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        }
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) throws TrainingException {
        if (room == null || room.trim().equals("")) {
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        }
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void addTrainee(Trainee trainee) {
        trainees.add(trainee);
    }

    public void removeTrainee(Trainee trainee) throws TrainingException {
        int before = trainees.size();
        trainees.remove(trainee);
        int after = trainees.size();
        if (before == after) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

/*    public void removeTrainee(Trainee trainee) throws TrainingException {
        if (!trainees.contains(trainee)) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        trainees.remove(trainee);
    }*/

    public void removeTrainee(int index) throws TrainingException {
        try {
            trainees.remove(index);
        } catch (IndexOutOfBoundsException ex) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
    }

    public Trainee getTraineeByFirstName(String firstName) throws TrainingException {
        for (Trainee elem : trainees) {
            if (elem.getFirstName().equals(firstName)) {
                return elem;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public Trainee getTraineeByFullName(String fullName) throws TrainingException {
        for (Trainee elem : trainees) {
            if (elem.getFullName().equals(fullName)) {
                return elem;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void sortTraineeListByFirstNameAscendant() {
        trainees.sort(Comparator.comparing(Trainee::getFirstName));
    }

    public void sortTraineeListByRatingDescendant() {
        trainees.sort(Comparator.comparingInt(Trainee::getRating));
        Collections.reverse(trainees);
    }

    public void reverseTraineeList() {
        Collections.reverse(trainees);
    }

    public void rotateTraineeList(int positions) {
        Collections.rotate(trainees, positions);
    }

    public List<Trainee> getTraineesWithMaxRating() throws TrainingException {
        List<Trainee> traineesWithMaxRating = new ArrayList<>();
        int maxRating = 0;
        for (Trainee elem : trainees) {
            if (maxRating < elem.getRating()) {
                maxRating = elem.getRating();
                traineesWithMaxRating.clear();
                traineesWithMaxRating.add(elem);
            } else if (maxRating == elem.getRating()) {
                traineesWithMaxRating.add(elem);
            }
        }
        if (traineesWithMaxRating.isEmpty()) {
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        return traineesWithMaxRating;
    }

    public boolean hasDuplicates() {
        Set<Trainee> set = new HashSet<>(trainees);
        return set.size() < trainees.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (name != null ? !name.equals(group.name) : group.name != null) return false;
        if (room != null ? !room.equals(group.room) : group.room != null) return false;
        return trainees != null ? trainees.equals(group.trainees) : group.trainees == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (trainees != null ? trainees.hashCode() : 0);
        return result;
    }
}
