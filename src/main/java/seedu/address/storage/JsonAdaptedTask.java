package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Deadline;
import seedu.address.model.assignment.ModuleCode;
import seedu.address.model.assignment.Name;
import seedu.address.model.assignment.Remind;
import seedu.address.model.assignment.Task;
import seedu.address.model.lesson.Lesson;

public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String name;
    private final String deadline;
    private final boolean isReminded;
    private final String endTime;
    private final String moduleCode;

    /**
     * Constructs a {@code JsonAdaptedAssignment} with the given assignment details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("deadline") String deadline,
                                 @JsonProperty("endTime") String endTime,
                                 @JsonProperty("module") String moduleCode,
                                 @JsonProperty("isReminded") boolean isReminded) {
        this.name = name;
        this.moduleCode = moduleCode;
        this.endTime = endTime;
        this.deadline = deadline;
        this.isReminded = isReminded;
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        if (source instanceof Assignment) {
            name = source.getName().fullName;
            deadline = source.getTime().value;
            moduleCode = source.getModuleCode().moduleCode;
            isReminded = ((Assignment) source).isReminded();
            endTime = "";
        } else {
            name = source.getName().fullName;
            moduleCode = source.getModuleCode().moduleCode;
            deadline = source.getTime().value;
            endTime = ((Lesson) source).getEndTime().value;
            isReminded = false;
        }
    }

    /**
     * Converts this Jackson-friendly adapted assignment object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assignment.
     */
    public Task toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (deadline == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        if (moduleCode == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName()));
        }

        if (!ModuleCode.isValidModuleCode(moduleCode)) {
            throw new IllegalValueException(ModuleCode.MESSAGE_CONSTRAINTS);
        }
        final ModuleCode modelModuleCode = new ModuleCode(moduleCode);

        final Remind modelRemind = new Remind(isReminded);

        if (endTime == null) {
            throw new IllegalValueException(String.format(
                    MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName()));
        }

        if (endTime.equals("")) {
            return new Assignment(modelName, modelDeadline, modelModuleCode, modelRemind);
        } else {
            return new Lesson(modelName, modelDeadline, new Deadline(endTime), modelModuleCode);
        }

    }
}