package AC2015;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class ClemExperiment {

	
	
	public List<ClemClass> ListClemClass;

	public static void main(String[] args) {

		ClemExperiment cl = new ClemExperiment();
		
		
		

		List<Integer> testListInt = new ArrayList<Integer>();
		testListInt.add(42);
		testListInt.add(42);
		testListInt.add(50);
				
		
		Predicate<Integer> p = i -> i.intValue() == 42;
		
		List<Integer> testListIntResult = Common.Where(testListInt, p);
		
		System.out.println(testListIntResult.size());
		System.out.println(testListIntResult.get(0));
		System.out.println(testListIntResult.get(1));
		
		int valueint = testListIntResult.get(0);
		
		System.out.println(valueint);
		
		
		
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
		Predicate<ClemClass> Is42AndContainsReponse = c -> c.intClem == 42 && c.stringClem.contains("reponse");
		Predicate<ClemClass> ConditionImpossible = c -> c.intClem == 42 && c.intClem == 43;
//		
//		
//		List<ClemClass> resultInt1 = 
//				cl.ListClemClass.stream()
//				.filter(int1Is1)
//				.collect(Collectors.toList());
		
		List<ClemClass> resultInt1 = Common.Where(cl.ListClemClass, int1Is1);
		List<ClemClass> result = Common.Where(cl.ListClemClass, ConditionImpossible);
		
		
		ClemClass firstresult = Common.First(cl.ListClemClass, int1Is1);
		
		
		System.out.println(result.size());
		if (result.size() != 0)
			System.out.println(result.get(0).stringClem);
		else 
			System.out.println("pas de result");
		
		System.out.println(resultInt1.toString());
		System.out.println(resultInt1.toArray().length);	
				
		System.out.println("test du foreach avec lambda");
		resultInt1.forEach(a -> System.out.println(a.stringClem));		
		
		Predicate<ClemClass> predicateForRemove = ccitem -> ccitem.intClem2 > 0;
		
		cl.ListClemClass.removeIf(predicateForRemove);
		
		System.out.println(cl.ListClemClass.toString());
		System.out.println(cl.ListClemClass.toArray().length);		
		
		
		Date currentDate = new Date();		
		String dateFormatString = Common.FilePrefixdateFormat.format(currentDate);
		
		System.out.println(dateFormatString);
		
		Solution testSolution = new Solution();
		testSolution.testSolListClemClass.add(clemcl6);
		testSolution.testSolListClemClass.add(clemcl5);
				
		testSolution.SaveSolutionAsRaw("TestSolutionSerialize");
		
		
		Solution desSolution = 
				(Solution)(Common.FU.DeserializeFileToObject("C:\\ACFile\\TestSolutionSerialize"));
		
		System.out.println(desSolution.testSolListClemClass.toString());
		
		
		List<ClemClass> deepCopytest = Common.DeepCopy(cl.ListClemClass);
		
		System.out.println("Deep Copy test");
		System.out.println(deepCopytest.toString());
		
		System.out.println(cl.ListClemClass.toString());
		System.out.println(deepCopytest.get(2).stringClem);
		
	}
	
	// Constructeur
	public ClemExperiment()
	{
		;
	}
	
	
}
