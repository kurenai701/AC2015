package AC2015;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ClemExperiment {

	
	
	public List<ClemClass> ListClemClass;

	public static void main(String[] args) {

		ClemExperiment cl = new ClemExperiment();
		cl.ListClemClass = new ArrayList<ClemClass>();

		// initialiser la liste
	
		ClemClass clemcl1 = new ClemClass(0,0,"test");
		ClemClass clemcl2 = new ClemClass(1,0,"test");
		ClemClass clemcl3 = new ClemClass(1,1,"testing");
		ClemClass clemcl4 = new ClemClass(2,1,"test");
		ClemClass clemcl5 = new ClemClass(3,3,"blabla");
		ClemClass clemcl6 = new ClemClass(42,0,"c est la reponse");
		
		cl.ListClemClass.add(clemcl1);
		cl.ListClemClass.add(clemcl2);
		cl.ListClemClass.add(clemcl3);
		cl.ListClemClass.add(clemcl4);
		cl.ListClemClass.add(clemcl5);
		cl.ListClemClass.add(clemcl6);

		System.out.println(cl.ListClemClass.toString());	
		System.out.println(cl.ListClemClass.toArray().length);		
		
		// mes predicats
		Predicate<ClemClass> stringIsTest = item -> item.stringClem == "test";
		Predicate<ClemClass> int1Is1 = c -> c.intClem == 1;
		
		
		List<ClemClass> resultStringTest = cl.ApplyPredicateToList(stringIsTest, cl.ListClemClass);
		List<ClemClass> resultInt1 = 
				cl.ListClemClass.stream()
				.filter(int1Is1)
				.collect(Collectors.toList());
		
		
		System.out.println(resultStringTest.toString());	
		System.out.println(resultStringTest.toArray().length);			
		
		System.out.println(resultInt1.toString());
		System.out.println(resultInt1.toArray().length);	
		
		
		
		
		Predicate<ClemClass> predicateForRemove = ccitem -> ccitem.intClem2 > 0;
		
		cl.ListClemClass.removeIf(predicateForRemove);
		
		System.out.println(cl.ListClemClass.toString());
		System.out.println(cl.ListClemClass.toArray().length);		
		
	}
	
	// Constructeur
	public ClemExperiment()
	{
		;
	}
	
	

	List<ClemClass> ApplyPredicateToList(Predicate<ClemClass>  pred, List<ClemClass> listCC)
	{
		List<ClemClass> resultList = new ArrayList<ClemClass>();
		for(ClemClass cc : listCC)
		{
			if(pred.test(cc))
			{
				resultList.add(cc);
			}
		}
		return resultList;
	}
	
		
	public <T> List<T> WherePredicate(List<T> list, Predicate<T> pred)
	{
		List<T> resultList = 
				list.stream()
				.filter(pred)
				.collect(Collectors.toList());
	
		return resultList;		
	}
	
}
