/*
 * Copyright 2016
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

package de.tudarmstadt.ukp.experiments.argumentation.sequence.feature.sentiment;

import de.tudarmstadt.ukp.experiments.argumentation.sequence.feature.AbstractUnitSentenceFeatureGenerator;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.sentiment.type.StanfordSentimentAnnotation;
import org.apache.log4j.Logger;
import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.Feature;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.apache.uima.fit.util.JCasUtil.selectCovered;

/**
 * Returns five double-valued features for very neg, neg, neu, pos, very pos from Stanford
 * sentiment analysis
 *
 * @author Ivan Habernal
 */
public class StanfordSentimentUFE
        extends AbstractUnitSentenceFeatureGenerator
{
    static Logger log = Logger.getLogger(StanfordSentimentUFE.class);

    @Override
    protected Set<Feature> extract(JCas jCas, Sentence sentence, String sentencePrefix)
            throws TextClassificationException
    {
        Set<Feature> result = new HashSet<>();
        List<StanfordSentimentAnnotation> sentimentAnnotations = selectCovered(
                StanfordSentimentAnnotation.class, sentence);
        if (sentimentAnnotations.size() != 1) {
            log.warn("No sentiment annotations for sentence " + sentence.getCoveredText());

            // https://code.google.com/p/dkpro-tc/issues/detail?id=210
            //            result.add(new Feature("sentimentVeryNegative", null));
            //            result.add(new Feature("sentimentNegative", null));
            //            result.add(new Feature("sentimentNeutral", null));
            //            result.add(new Feature("sentimentPositive", null));
            //            result.add(new Feature("sentimentVeryPositive", null));
        }
        else {
            StanfordSentimentAnnotation sentiment = sentimentAnnotations.get(0);

            result.add(new Feature(sentencePrefix + "sentimentVeryNegative",
                    sentiment.getVeryNegative()));
            result.add(new Feature(sentencePrefix + "sentimentNegative", sentiment.getNegative()));
            result.add(new Feature(sentencePrefix + "sentimentNeutral", sentiment.getNeutral()));
            result.add(new Feature(sentencePrefix + "sentimentPositive", sentiment.getPositive()));
            result.add(new Feature(sentencePrefix + "sentimentVeryPositive",
                    sentiment.getVeryPositive()));
        }
        return result;
    }
}
