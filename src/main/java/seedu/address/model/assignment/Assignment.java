package seedu.address.model.assignment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents an Assignment in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Assignment extends Task {
    private final Remind remind;

    /**
     * Every field must be present and not null.
     */
    public Assignment(Name name, Deadline deadline, ModuleCode moduleCode, Remind remind) {
        super(name, deadline, moduleCode);
        requireAllNonNull(name, deadline, moduleCode, remind);
        this.remind = remind;
    }

    public Deadline getDeadline() {
        return super.getTime();
    }

    public Remind getRemind() {
        return remind;
    }

    /**
     * Returns true if both assignments of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two assignments.
     */
    public boolean isSameAssignment(Assignment otherAssignment) {
        if (otherAssignment == this) {
            return true;
        }

        return otherAssignment != null
                && otherAssignment.getName().equals(getName())
                && (otherAssignment.getDeadline().equals(getDeadline()));
    }

    /**
     * Returns true if the assignment already has reminders set. Otherwise, returns false.
     *
     * @return true if the assignment already has reminders set. Otherwise, returns false
     */
    public boolean isReminded() {
        return remind.isReminded();
    }

    /**
     * Returns true if both assignments have the same identity and data fields.
     * This defines a stronger notion of equality between two assignments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Assignment)) {
            return false;
        }

        Assignment otherAssignment = (Assignment) other;
        return otherAssignment.getName().equals(getName())
                && otherAssignment.getDeadline().equals(getDeadline())
                && otherAssignment.getModuleCode().equals(getModuleCode());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(super.getName(), getDeadline(), super.getModuleCode());
    }

    // TODO: Consider printing Remind as well
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Deadline: ")
                .append(getDeadline())
                .append(" Module: ")
                .append(getModuleCode());
        return builder.toString();
    }

}
