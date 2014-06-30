/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import MixPolicyAlgoFirst.MixStrategy_Algorithm;
import PrimalAffine.PrimalAffine_Algorithm;
import Simplex.Simplex_Algorithm;
import gnuplotutils.Gnu_Script_Writer;
import input.MDPData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import policyiteration.LeastIndexPivotModifiedPolicyIterationImpl;
import policyiteration.LeastIndexPivotPolicyIterationImpl;
import policyiteration.ModifiedPolicyIterationImpl;
import policyiteration.PolicyIterationImpl;
import policyiteration.SinglePivotModifiedPolicyIterationImpl;
import policyiteration.SinglePivotPolicyIterationImpl;
import valueiteration.ValueIterationImpl;

/**
 *
 * @author vinit
 */
public class GUI_Algo_Connector {
    
    private String algoname;
    
    private MDPData mdp;
    
    
    
    
    public void connector(MDPData mdpdata, String algo){
        
        mdp =  mdpdata;
        
        algoname =  algo;
        
        String path = "";
        if(algo.equals("simplex"))
        {
            System.out.println("IN GUI CONNECTOR");
            new Simplex_Algorithm(mdp).runSimplexAlgorithm(0);
        
            path = "Simplex";
        
            Gnu_Script_Writer gnuImager = new Gnu_Script_Writer(mdp.getNoOfStates(), mdp.getNumOfActionsOnEachState(), path);

        }
        
        else if(algo.equals("primal affine"))
        {
            new PrimalAffine_Algorithm(mdp).runPrimalAffine();
        
            path = "Primal_Affine";
        
            new Gnu_Script_Writer(mdp.getNoOfStates(), mdp.getNumOfActionsOnEachState(), path);

        }
        
        else if(algo.equals("cute algo"))
        {
            path = "MixStrategy";
        
            new MixStrategy_Algorithm(mdp).runMixStrategy();
        
            new Gnu_Script_Writer(mdp.getNoOfStates(), mdp.getNumOfActionsOnEachState(), path);

        }
        
        else if(algo.equals("value iteration"))
        {
            path = "ValueIteration";
            new ValueIterationImpl(mdp).run();

        }
        
        else if(algo.equals("policy iteration")){
            path = "PolicyIteration";
            new PolicyIterationImpl(mdp).run();

        }
        else if(algo.equals("Modified policy iteration")){
            path = "ModifiedPolicyIteration";
            new ModifiedPolicyIterationImpl(mdp).run();

        }
        else if(algo.equals("Bland simplex on dual")){
            path = "Simplex";
            new Simplex_Algorithm(mdp).runSimplexAlgorithm(1);
            new Gnu_Script_Writer(mdp.getNoOfStates(), mdp.getNumOfActionsOnEachState(), path);

        }
        else if(algo.equals("Single pivot policy iteration")){
            path = "SinglePivotPolicyIteration";
            new SinglePivotPolicyIterationImpl(mdp).run();

        }
        else if(algo.equals("Single pivot modified policy iteration")){
            path = "SinglePivotModifiedPolicyIteration";
            new SinglePivotModifiedPolicyIterationImpl(mdp).run();

        }
        else if(algo.equals("Least index policy iteration")){
            path = "LastIndexPivotPolicyIteration";
            new LeastIndexPivotPolicyIterationImpl(mdp).run();

        }
        else if(algo.equals("Least index modified policy iteration")){
            path = "LastIndexPivotModifiedPolicyIteration";
            new LeastIndexPivotModifiedPolicyIterationImpl(mdp).run();

        }

        
    }
    
}
