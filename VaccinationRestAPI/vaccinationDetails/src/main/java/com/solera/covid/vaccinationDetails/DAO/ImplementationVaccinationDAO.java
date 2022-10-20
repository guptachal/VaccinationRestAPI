package com.solera.covid.vaccinationDetails.DAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.solera.covid.vaccinationDetails.entity.Vaccination;
import com.solera.covid.vaccinationDetails.exceptionHandling.GenericException;
import com.solera.covid.vaccinationDetails.exceptionHandling.IdNotFoundException;
import com.solera.covid.vaccinationDetails.exceptionHandling.TimeDifference;;


@Repository

public class ImplementationVaccinationDAO implements InterfaceVaccinationDAO {
	
	private EntityManager entityManager;
	public ImplementationVaccinationDAO(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	// TO DISPLAY ALL THE DATABASE IN SINGLE URL
	@Override
	public List<Vaccination> showAll() {
		// TODO Auto-generated method stub
		//INVOKING THE HIBERNATE  
		Session currentSession= entityManager.unwrap(Session.class);
		
		//CREATING THE QUERY
		Query theQuery = currentSession.createQuery("FROM Vaccination",Vaccination.class);
		
		//CREATING THE RESULTSET OR GETRESULTLIST
		List<Vaccination> vaccination = theQuery.getResultList(); 
		
		return vaccination;
	}
    // TO DISPLAY DETAILS AS PER THE HEALTH ID
	@Override
	public Vaccination showById(int healthId) {
		// TODO Auto-generated method stub
		Session currentSession= entityManager.unwrap(Session.class);
		Vaccination vac = currentSession.get(Vaccination.class,healthId);
		
		return vac;
	}

	// to create entry and to update to the database
	@Override
	public String saveOrUpdate(Vaccination vaccination) {
		
		// TODO Auto-generated method stub
		
		//FIRST DOSE -- SECOND DOSE 
		int doseCount = 0;
		// finding the seperate date for each vaccine
		Date firstVaxDate = vaccination.getFirstVaxDate();
		Date secondVaxDate =  vaccination.getSecondVaxDate();
		Date boosterDate = vaccination.getBoosterDoseDate();
		
		// checking the gender validation
		String gender = vaccination.getGender();
		if(gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("t"))
			;
		else
			throw new GenericException("Invalid Gender Entry!");
		
		// checking the age validation
		if(vaccination.getAge()<18)
			throw new GenericException("Invalid Age Entry!");
		
		
		if(firstVaxDate!=null)
			if(dateValid(firstVaxDate.toString()) == false)
				throw new GenericException("Invalid Date Format! ");
		
		
		
		// checking the format of date data entry
		if(firstVaxDate==null && secondVaxDate!=null || firstVaxDate == null && boosterDate!=null )
			throw new GenericException("First take your first dose !");

		// checking the format of date data entry
		if(secondVaxDate==null && boosterDate!=null)
			throw new GenericException("In-appropriate Vaccinatoin date data insertion....");
		
		
		// Validating the doses difference
		boolean secondDoseValid = seondVaxValidatoin(firstVaxDate, secondVaxDate);
		boolean boosterDoseValid = boosterDoseValidation(secondVaxDate,boosterDate);
				
		
		if(secondDoseValid == true && boosterDoseValid == true) {
			//Handling the status of first vaccine, second vaccine and booster dose
			if(firstVaxDate != null) {
				vaccination.setFirstVax(1);
				doseCount ++;
			}
			else
				vaccination.setFirstVax(0);
			if(secondVaxDate != null) {
				vaccination.setSecondVax(1);
				doseCount ++;
			}
			else
				vaccination.setSecondVax(0);
			if(boosterDate != null) {
				vaccination.setBoosterDose(1);
			}
			else
				vaccination.setBoosterDose(0);
			
			vaccination.setNoOfDose(doseCount);
			
			Session currentSession= entityManager.unwrap(Session.class);
			currentSession.saveOrUpdate(vaccination);
			
		}
		else {
			if(secondDoseValid == false) {
				throw new TimeDifference("The ideal difference between the two dose must be greater then 120 days!!");
			}
			if(boosterDoseValid == false) {
				throw new com.solera.covid.vaccinationDetails.exceptionHandling.TimeDifference("The ideal difference between the 2nd Dose and Booster dose must be 270 days!!");
			}

		}
		return "Sucessfully added!!";
	}
	
	
	// method to find and validate date diiference b/w first dose and second dose
	private boolean boosterDoseValidation(Date firstDate, Date secondDate) {
		// TODO Auto-generated method stub
		if(firstDate == null && secondDate == null)
			return true;
		if(secondDate == null)
			return true;
		if(firstDate == null && secondDate!= null)
			return false;
		int days = dateDiffernce(firstDate,secondDate);
		if(days>270) 
			return true;
		
		return false;
		
	}

	// method to find and validate date diiference b/w first dose and second dose
	private boolean seondVaxValidatoin(Date firstDate, Date secondDate) {
		// TODO Auto-generated method stub
		if(firstDate == null && secondDate == null)
			return true;
		if(secondDate == null)
			return true;
		if(firstDate == null && secondDate!= null)
			return false;
		int days = dateDiffernce(firstDate,secondDate);
		if(days>120) 
			return true;
		
		return false;
	}
	
	// find the difference between two dates
	private int dateDiffernce(Date d1, Date d2) {
		long dateBeforeInMs = d1.getTime();
		long dateAfterInMs = d2.getTime();
	 if(dateAfterInMs!=0 ) {
		long timeDiff = (dateAfterInMs - dateBeforeInMs);
	 
		 int daysDiff = (int) (timeDiff / (1000 * 60 * 60 * 24));
		return daysDiff;
	 }
	 else {
		 return 0;
	 }
	}

	// getiing the list of people based on the no of doses
	@Override
	public List<Vaccination> vaccinatedList(int noOfDose) {
		// TODO Auto-generated method stub
	
		Session currentSession= entityManager.unwrap(Session.class);
		Query theQuery = currentSession.createQuery("from Vaccination WHERE NO_OF_DOSE = :doses",Vaccination.class);
		theQuery.setParameter("doses", noOfDose);
		List<Vaccination> vax = theQuery.getResultList();
		if(vax == null) {
			throw new GenericException("Only upto value 3 are accepted");
		}
		
		return vax;
	}

	
	// value added function to delete the entry with two doses!
	@Override
	public int delete(int noOfDoses) {
		// TODO Auto-generated method stub
		Session currentSession= entityManager.unwrap(Session.class);
		Query theQuery = currentSession.createQuery("DELETE FROM Vaccination WHERE noOfDose= 2");
		
		theQuery.executeUpdate();
		return 1;
	}

	
	// to delete the data recors with the booster dose
	@SuppressWarnings("unused")
	@Override
	public void deleteById(int healthId) {
		// TODO Auto-generated method stub
		Session currentSession= entityManager.unwrap(Session.class);
		Vaccination vaccination = showById(healthId);
		int boosterDose = vaccination.getBoosterDose();
		
		if(boosterDose!=1) {
			throw new GenericException("Only Vaccinated people with booster dose can be deleted!");
		}
		if(vaccination == null) {
			throw new IdNotFoundException("No record with id "+ healthId +" exist!");
		}
		Query theQuery = currentSession.createQuery("DELETE FROM Vaccination WHERE boosterDose = 1 AND healthId =:Id");
		theQuery.setParameter("Id",healthId);
		theQuery.executeUpdate();
		
	}

	// getting the list of the people with the vaccination type
	@Override
	public List<Vaccination> vaccineByType(String vaccineName) {
		// TODO Auto-generated method stub

		Session currentSession= entityManager.unwrap(Session.class);
		Query theQuery = currentSession.createQuery("FROM Vaccination WHERE vaxType= :name",Vaccination.class);
		theQuery.setParameter("name", vaccineName);
		List<Vaccination> vac = theQuery.getResultList();
		if(vac == null) {
			throw new GenericException("No vaccine with such name exist!");
		}
		
		return vac;
	}

	// creating the report based on vax type
	@SuppressWarnings("unchecked")
	@Override
	public List<Vaccination> vaccineCountReprt() {
		// TODO Auto-generated method stub
		Session currentSession= entityManager.unwrap(Session.class);
		Query<Vaccination> theQuery = currentSession.createQuery("SELECT COUNT(healthId), vaxType FROM Vaccination GROUP BY vaxType");
		List<Vaccination> vac = theQuery.getResultList();
		if(vac.size() == 0) {
			throw new GenericException("No data have been entered so far, kindly enter to create the report");
		}
		
		return vac;
	}
	
	// for future value addition!
	 public static boolean dateValid(final String date) {

	        boolean valid = false;

	        try {

	            // ResolverStyle.STRICT for 30, 31 days checking, and also leap year.
	            LocalDate.parse(date,
	                    DateTimeFormatter.ofPattern("uuuu-M-d")
	                            .withResolverStyle(ResolverStyle.STRICT)
	            );

	            valid = true;

	        } catch (DateTimeParseException e) {
	              valid = false;
	        }

	        return valid;
	    }

}
