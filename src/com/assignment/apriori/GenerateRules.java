package com.assignment.apriori;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GenerateRules {

	public static List<String> combination = new ArrayList<String>();

	public static List<RuleModel> _rules = new ArrayList<RuleModel>();

	public static Map<String, Float> frequentItemSet = new HashMap<String, Float>();

	public static void combinationUtil(String arr[], int size, int itemSetCount, int index, String data[], int i) {
		// Current combination is ready to be printed, print it
		if (index == itemSetCount) {

			String temp = "";
			for (int j = 0; j < itemSetCount; j++) {
				temp += data[j];
				if (j < itemSetCount - 1) {
					temp += ",";
				}
			}
			combination.add(temp);
			return;
		}

		// When no more elements are there to put in data[]
		if (i >= size)
			return;

		// current is included, put next at next location
		data[index] = arr[i];
		combinationUtil(arr, size, itemSetCount, index + 1, data, i + 1);

		// current is excluded, replace it with next (Note that
		// i+1 is passed, but index is not changed)
		combinationUtil(arr, size, itemSetCount, index, data, i + 1);
	}

	static void getSubSets(String arr[], int n, int itemsets) {
		// A temporary array to store all combination one by one
		String data[] = new String[itemsets];

		// Print all combination using temprary array 'data[]'
		combinationUtil(arr, n, itemsets, 0, data, 0);
	}

	static void generateRules(List<String> combination, Map.Entry<String, Float> _subSet, float minConfidence) {

		for (String _comb : combination) {

//			System.out.print(_comb);
			String remaining = getRemaining(Arrays.asList(_subSet.getKey().toString().split(",")), _comb);
//			System.out.println("------>" + remaining);

			RuleModel _model = new RuleModel();
			_model.setSupportCount(_subSet.getValue());
			_model.setLhs(_comb);
			_model.setRhs(remaining);
//			System.out.println(_subSet.getKey()+"------"+frequentItemSet.get(_subSet.getKey()));
			if(frequentItemSet.get(_comb)==null){
				if(_comb.split(",").length==3){
					System.out.println(_comb);
					System.out.println(_comb.toString().split(",")[1]+","+_comb.toString().split(",")[0]);
					System.out.println(frequentItemSet.get(_subSet.getKey()));
					if(frequentItemSet.get(_comb.toString().split(",")[1]+","+_comb.toString().split(",")[0]+","+_comb.toString().split(",")[2])!=null){
						System.out.println(frequentItemSet.get(_comb.toString().split(",")[1]+","+_comb.toString().split(",")[0]+","+_comb.toString().split(",")[2]));
						_model.setConfidence(frequentItemSet.get(_subSet.getKey()) / frequentItemSet.get(_comb.toString().split(",")[1]+","+_comb.toString().split(",")[0]+","+_comb.toString().split(",")[2]));

					}else if(frequentItemSet.get(_comb.toString().split(",")[2]+","+_comb.toString().split(",")[0]+","+_comb.toString().split(",")[1])!=null){
						System.out.println(frequentItemSet.get(_comb.toString().split(",")[2]+","+_comb.toString().split(",")[0]+","+_comb.toString().split(",")[1]));
						_model.setConfidence(frequentItemSet.get(_subSet.getKey()) / frequentItemSet.get(_comb.toString().split(",")[2]+","+_comb.toString().split(",")[0]+","+_comb.toString().split(",")[1]));

					}else if(frequentItemSet.get(_comb.toString().split(",")[0]+","+_comb.toString().split(",")[2]+","+_comb.toString().split(",")[1])!=null){
						System.out.println(frequentItemSet.get(_comb.toString().split(",")[0]+","+_comb.toString().split(",")[2]+","+_comb.toString().split(",")[1]));
						_model.setConfidence(frequentItemSet.get(_subSet.getKey()) /frequentItemSet.get(_comb.toString().split(",")[0]+","+_comb.toString().split(",")[2]+","+_comb.toString().split(",")[1]));

					}else if(frequentItemSet.get(_comb.toString().split(",")[2]+","+_comb.toString().split(",")[1]+","+_comb.toString().split(",")[0])!=null){
						System.out.println(frequentItemSet.get(_comb.toString().split(",")[2]+","+_comb.toString().split(",")[1]+","+_comb.toString().split(",")[0]));
						_model.setConfidence(frequentItemSet.get(_subSet.getKey()) / frequentItemSet.get(_comb.toString().split(",")[2]+","+_comb.toString().split(",")[1]+","+_comb.toString().split(",")[0]));

					}
				}else{
					System.out.println(_comb);
					if(frequentItemSet.get(_comb)!=null){
						_model.setConfidence(frequentItemSet.get(_subSet.getKey()) / frequentItemSet.get(_comb));	
					}else{
						System.out.println(_comb.toString().split(",")[1]+","+_comb.toString().split(",")[0]);
						System.out.println(frequentItemSet.get(_subSet.getKey()));
						System.out.println(frequentItemSet.get(_comb.toString().split(",")[1]+","+_comb.toString().split(",")[0]));
						_model.setConfidence(frequentItemSet.get(_subSet.getKey()) / frequentItemSet.get(_comb.toString().split(",")[1]+","+_comb.toString().split(",")[0]));
					}		
				}
				

			}else{
				System.out.println("---------");
				_model.setConfidence(frequentItemSet.get(_subSet.getKey()) / frequentItemSet.get(_comb));
			}
			_rules.add(_model);
		}
	}

	static String getRemaining(List<String> _firstSet, String _comb) {

		List<String> _combList = new LinkedList<String>(Arrays.asList(_comb.split(",")));
		List<String> _firstSet1 = new LinkedList<String>(_firstSet);

		for (String _outer : _firstSet) {
			for (String _inner : _combList) {
				if (_outer.equals(_inner)) {
					_firstSet1.remove(_outer);
					break;
				}
			}
		}
		return _firstSet1.toString();
	}

	static void generateListToCalculateConfidence() {

		for (Map.Entry<Integer, Map<String, Float>> _frequentSet : Apriori.frequentItemSet.entrySet()) {
			for (Map.Entry<String, Float> _set : _frequentSet.getValue().entrySet()) {
				frequentItemSet.put(_set.getKey(), _set.getValue());
			}
		}
	}
}
