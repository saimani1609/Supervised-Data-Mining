# Supervised-Data-Mining
Weka, J48 Classifiers


Aim of the Project:
The project is to perform a supervised data mining i.e. classification on a data set using two
algorithms.
The algorithms which I chose are:
 Random Forest (Weka)
 Decision Tree (J48)

ALGORITHM/TOOL/ DATA SOURCE
ALGORITHM :
The Classification algorithms chosen for this project :
1. Random Forests (Category 2) - Weka
2. Decision Tree (Decision Tree) – J48

Random Forests Classification
Random Forests are broadly believed to be the finest “off-the- shelf” classifiers envisaging high-
dimensional data. It is an assortment of tree predictors such that each tree relies on the values of a
random vector sampled autonomously and distributed equally for all trees in the forest. The
generalization error for forest touches to a limit as the number of trees in the forest becomes hefty.
The generalization error of a forest of tree classifiers relies on the strength of the individual trees
and the association between them in the forest. Different subset of training data is selected, with
replacement, to train each tree. Remaining training data are used to estimate variable of importance
and errors.
J48 Classification
J48 classifier is a straightforward C4.5 decision tree for classification, which creates a binary tree. It is
most useful decision tree approach for classification problems. This technique constructs a tree to
model the classification process. After the tree is built, the algorithm is applied to each tuple in the
database and results in classification for that tuple.

While building a decision tree, J48 omits the missing values i.e. the value for that item can be
predicted based on what is known about the attribute values for the other records. The key idea is to
split the data into range based on the attribute values for that item that are identified in the training
sample.




Dataset Used: ionosphere.arff
Relation : ionosphere
Instances: 351
Attributes: 34
Source : The dataset can be downloaded from the link below
http://repository.seasr.org/Datasets/UCI/arff/ionosphere.arff
