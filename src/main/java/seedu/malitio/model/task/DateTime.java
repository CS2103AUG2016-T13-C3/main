package seedu.malitio.model.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import seedu.malitio.commons.exceptions.IllegalValueException;
import seedu.malitio.logic.parser.DateParser;

//@@author a0126633j

/**
 * Represents a date and time of an event or deadline
 */
public class DateTime {
    public static final String MESSAGE_DATETIME_CONSTRAINTS = "Unrecognised date and time!";
    
    private Date date;
    
    private static DateFormat outputFormatter = new SimpleDateFormat("dd-MMM-yyyy, HH:mm (EEE)");
  
    /**
     * Converts the string that contains date information into Date
     * 
     * @throws IllegalValueException if the format of date is unrecognised
     */
    public DateTime(String date) throws IllegalValueException {

       this.date = DateParser.parse(date);
       if (this.date == null) {
           throw new IllegalValueException(MESSAGE_DATETIME_CONSTRAINTS);
       }
    }
    
    public String toString() {
        String newDateString = outputFormatter.format(date);
        return newDateString;
    }

    /**
     * Compares the argument date/time with the object's date/time.
     * 
     * @returns the value 0 if the argument Date is equal to this Date; 
     * a value less than 0 if this Date is before the Date argument; 
     * and a value greater than 0 if this Date is after the Date argument  
     */
	public int compareTo(DateTime dateTime) {
		return date.compareTo(dateTime.getDate());
	}
	
	public int compareTo(Date date) {
		return this.date.compareTo(date);
	}

	public Date getDate() {
		return date;
	}
}
