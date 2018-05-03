	import java.lang.reflect.Constructor;
	import java.lang.reflect.InvocationTargetException;
	

	import org.jdmp.core.algorithm.classification.AbstractClassifier;
	import org.jdmp.core.dataset.ListDataSet;
	import org.jdmp.weka.wrappers.DataSetToInstancesWrapper;
	import org.jdmp.weka.wrappers.SampleToInstanceWrapper;
	import org.ujmp.core.Matrix;
	

	import weka.core.Instance;
	import weka.core.Instances;
	

	public class WekaClassifier extends AbstractClassifier {
		private static final long serialVersionUID = 29290678735702499L;
	

		public enum WekaClassifierType {
			BayesNet, NaiveBayes, NaiveBayesMultinomial, NaiveBayesMultinomialUpdateable, NaiveBayesUpdateable, LinearRegression, Logistic, MultilayerPerceptron, SGD, SimpleLinearRegression, SimpleLogistic, SMO, SMOreg, VotedPerceptron, IBk, KStar, LWL, AdaBoostM1, AdditiveRegression, AttributeSelectedClassifier, Bagging, ClassificationViaRegression, CostSensitiveClassifier, CVParameterSelection, FilteredClassifier, LogitBoost, MultiClassClassifier, MultiScheme, RandomCommittee, RandomSubSpace, RegressionByDiscretization, Stacking, Vote, DecisionTable, JRip, M5Rules, OneR, PART, Rule, ZeroR, DecisionStump, HoeffdingTree, J48, LMT, M5P, RandomForest, RandomTree, REPTree, GaussianProcesses
		};
	

		private weka.classifiers.AbstractClassifier wekaClassifier = null;
	

		private Instances instances = null;
	

		private WekaClassifierType classifierName = null;
	

		private boolean discrete = false;
	

		public WekaClassifier(WekaClassifierType classifierName, boolean discrete) {
			setLabel("Weka-" + classifierName);
			this.classifierName = classifierName;
			this.discrete = discrete;
			try {
				createAlgorithm();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	

		public void setOptions(String... options) throws Exception {
			wekaClassifier.setOptions(options);
		}
	

		public void createAlgorithm() throws ClassNotFoundException, NoSuchMethodException,
				SecurityException, InstantiationException, IllegalAccessException,
				IllegalArgumentException, InvocationTargetException {
			Class<?> c = null;
			if (c == null) {
				try {
					c = Class.forName(classifierName.name());
				} catch (ClassNotFoundException e) {
				}
			}
			if (c == null) {
				try {
					c = Class.forName("weka.classifiers." + classifierName.name());
				} catch (ClassNotFoundException e) {
				}
			}
			if (c == null) {
				try {
					c = Class.forName("weka.classifiers.bayes." + classifierName.name());
				} catch (ClassNotFoundException e) {
				}
			}
			if (c == null) {
				try {
					c = Class.forName("weka.classifiers.pmml.consumer." + classifierName.name());
				} catch (ClassNotFoundException e) {
				}
			}
			if (c == null) {
				try {
					c = Class.forName("weka.classifiers.functions." + classifierName);
				} catch (ClassNotFoundException e) {
				}
			}
			if (c == null) {
				try {
					c = Class.forName("weka.classifiers.lazy." + classifierName);
				} catch (ClassNotFoundException e) {
				}
			}
			if (c == null) {
				try {
					c = Class.forName("weka.classifiers.meta." + classifierName);
				} catch (ClassNotFoundException e) {
				}
			}
			if (c == null) {
				try {
					c = Class.forName("weka.classifiers.mi." + classifierName);
				} catch (ClassNotFoundException e) {
				}
			}
			if (c == null) {
				try {
					c = Class.forName("weka.classifiers.misc." + classifierName);
				} catch (ClassNotFoundException e) {
				}
			}
			if (c == null) {
				try {
					c = Class.forName("weka.classifiers.trees." + classifierName);
				} catch (ClassNotFoundException e) {
				}
			}
			if (c == null) {
				try {
					c = Class.forName("weka.classifiers.rules." + classifierName);
				} catch (ClassNotFoundException e) {
				}
			}
			if (c == null) {
				throw new ClassNotFoundException("class not found: " + classifierName);
			} else {
				Constructor<?> constr = c.getConstructor(new Class[] {});
				wekaClassifier = (weka.classifiers.AbstractClassifier) constr
						.newInstance(new Object[] {});
			}
		}
	

		public Matrix predictOne(Matrix input) {
			try {
				double[] probabilities = null;
				Instance instance = new SampleToInstanceWrapper(input, null, null, discrete, true);
				instance.setDataset(instances);
				probabilities = wekaClassifier.distributionForInstance(instance);
				double[][] v = new double[1][];
				v[0] = probabilities;
				Matrix output = Matrix.Factory.linkToArray(v);
				return output;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	

		public void trainAll(ListDataSet dataSet) {
			try {
				instances = new DataSetToInstancesWrapper(dataSet, discrete, true);
				wekaClassifier.buildClassifier(instances);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	

		public void reset() {
			try {
				createAlgorithm();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	

		public org.jdmp.core.algorithm.classification.Classifier emptyCopy() {
			return new WekaClassifier(classifierName, discrete);
		}
	

	}

