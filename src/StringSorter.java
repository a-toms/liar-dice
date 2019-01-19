import org.apache.commons.lang3.StringUtils;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringSorter {

	String raw;
	HashMap <Character, Integer>newmap;
	String sorted;
	ArrayList<String> freqCounts;

	public static void main(String[] args) {
		StringSorter stringSorter = new StringSorter();
		String test1 = "4234233";
		System.out.println(stringSorter.sortByFrequencyAndSize(test1));
	}

	public StringSorter() {
	}

	public String sortByFrequencyAndSize(String unsorted) {
		newmap = new HashMap<>();
		freqCounts = new ArrayList<>();
		sorted = "";
		raw = unsorted;
		createFrequencyMap();
		createListOfStringsByFrequency();
		return produceSortedString();
	}

	private void createFrequencyMap() {
		for (int i = 0; i < raw.length(); i++) {
			newmap.put(
					raw.charAt(i), StringUtils.countMatches(raw, raw.charAt(i))
			);
		}
	}

	private void createListOfStringsByFrequency() {
		Set set = newmap.entrySet();
		for (Object aSet : set) {
			Map.Entry me = (Map.Entry) aSet;
			String segment = StringUtils.repeat(me.getKey().toString(),
					Integer.parseInt(me.getValue().toString()));
			// We must convert object types to strings before converting to
			// integers. Why?
			freqCounts.add(segment);
		}
	}

	private String produceSortedString() {
		StringBuilder sorted = new StringBuilder();
		int n = freqCounts.size();
		for (int i = 0; i < n; i++) {
			int highFreq = 0;
			String highFreqString = "";
			for (String element : freqCounts) {
				if (element.length() > highFreq ||
						element.length() == highFreq
								&& isGreaterValue(highFreqString, element)){
					highFreqString = element;
					highFreq = element.length();
				}
			}
			sorted.append(highFreqString);
			freqCounts.remove(highFreqString);
		}
		return sorted.toString();
	}

	private boolean isGreaterValue(String current, String challenger){
		return Integer.valueOf(challenger) > Integer.valueOf(current);
	}

	public String sortBySize(String unsorted){
		String sorted = Stream.of(
				unsorted.split(""))
				.sorted()
				.collect(Collectors.joining()
		);
		StringBuilder descendingOrder = new StringBuilder(sorted).reverse();
		return descendingOrder.toString();
	}
}
