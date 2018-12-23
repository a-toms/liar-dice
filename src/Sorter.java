import org.apache.commons.lang3.StringUtils;

import javax.print.DocFlavor;
import java.util.*;



public class Sorter {

	public static void main(String[] args) {
		System.out.println("begin");
		Sorter sorter = new Sorter("24422133");
		sorter.sortByFrequency();
	}

	String raw;

	public Sorter(String raw){
		this.raw = raw;
	}


	public void sortByFrequency() {

		//todo: encapsulate the below. In the future, it is much easier to put objects into class structure
		// from the start. Do not write in an imperative manner and then encapsulate later. "Encapsulate later'" reduces
		// speed and increase mental load.

		// create hash map
		HashMap newmap = new HashMap();

		// populate hash map
		for (int i = 0; i < raw.length(); i++) {
			newmap.put(raw.charAt(i), StringUtils.countMatches(raw, raw.charAt(i)));
		}

		System.out.println("Values before remove: " + newmap);

		// Iterate over hashmap and add number, count as list to list
		ArrayList<String> freqCounts = new ArrayList<>();
		Set set = newmap.entrySet();
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry me = (Map.Entry) iterator.next();
			String cutUp = StringUtils.repeat(me.getKey().toString(), Integer.parseInt(me.getValue().toString()));
			// You must convert object types to strings before converting to integers. Why?
			freqCounts.add(cutUp);
		}
		System.out.println(freqCounts);

		// Sorts by 1. frequency and 2. value
		// Todo: encapsulate
		StringBuilder sorted = new StringBuilder();
		int n = 4; // todo: alter this to take an argument
		for (int i = 0; i < n; i++){
			int highFreq = 0;
			String highFreqString = "";
			for (String element : freqCounts){
				if (element.length() > highFreq || element.length() == highFreq && Integer.valueOf(element) > Integer.valueOf(highFreqString)){
					highFreqString = element;
					highFreq = element.length();
				}
			}
			sorted.append(highFreqString);
			freqCounts.remove(highFreqString);

		}
		System.out.println(sorted.toString());





		//Create StringBuilder
		StringBuilder builder = new StringBuilder();



		}
}

