/*
 * Copyright 2015
 * Ubiquitous Knowledge Processing (UKP) Lab
 * Technische Universität Darmstadt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.tudarmstadt.ukp.dkpro.argumentation.sequence.significance;

import java.io.File;
import java.util.SortedMap;

/**
 * @author Ivan Habernal
 */
public class TestSignificanceTwoFiles
{

    public static double compareId2OutcomeFiles(File f1, File f2,
            OutcomeSuccessMapReader outcomeSuccessMapReader)
    {
        SortedMap<String, Boolean> idResult1 = outcomeSuccessMapReader.readOutcomeSuccessMap(f1);
        SortedMap<String, Boolean> idResult2 = outcomeSuccessMapReader.readOutcomeSuccessMap(f2);

        if (!idResult1.keySet().equals(idResult2.keySet())) {
            throw new IllegalArgumentException("Instance IDs do not match!");
        }

        int[][] contingencyTable = new int[2][2];

        for (String key : idResult1.keySet()) {
            boolean res1 = idResult1.get(key);
            boolean res2 = idResult2.get(key);

            updateContingencyTable(res1, res2, contingencyTable);
        }

//        System.out.println("Cont. table:\n" + Arrays.toString(contingencyTable[0]) + "\n" +
//                Arrays.toString(contingencyTable[1]));

        return LiddellsExactTest.pValue(contingencyTable[0][1], contingencyTable[1][0]);
    }

    private static void updateContingencyTable(boolean firstMethodYes, boolean secondMethodYes,
            int[][] contingencyTable)
    {
        if (firstMethodYes) {
            if (secondMethodYes) {
                contingencyTable[0][0]++;
            }
            else {
                contingencyTable[0][1]++;
            }
        }
        else {
            if (secondMethodYes) {
                contingencyTable[1][0]++;
            }
            else {
                contingencyTable[1][1]++;
            }
        }
    }
}