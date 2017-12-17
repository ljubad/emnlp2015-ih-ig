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

package de.tudarmstadt.ukp.experiments.argumentation.sequence.feature.lexical;

import de.tudarmstadt.ukp.dkpro.core.api.frequency.util.FrequencyDistribution;
import org.apache.uima.fit.descriptor.TypeCapability;
import org.apache.uima.jcas.JCas;
import org.dkpro.tc.api.exception.TextClassificationException;
import org.dkpro.tc.api.features.Feature;
import org.dkpro.tc.api.features.FeatureExtractor;
import org.dkpro.tc.api.type.TextClassificationTarget;

import java.util.HashSet;
import java.util.Set;

/**
 * Extracts token n-grams within the given text classification unit
 */
@TypeCapability(inputs = { "de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence",
        "de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token" }) public abstract class LemmaLuceneNGramUFE
        extends LemmaLuceneNgramFeatureExtractorBase
        implements FeatureExtractor
{

    @Override
    public Set<Feature> extract(JCas jcas, TextClassificationTarget classificationUnit)
            throws TextClassificationException
    {
        Set<Feature> features = new HashSet<>();
        FrequencyDistribution<String> documentNGrams = LemmaNGramUtils
                .getAnnotationNGrams(jcas, classificationUnit, ngramLowerCase,
                        filterPartialStopwordMatches, ngramMinN, ngramMaxN, stopwords);

        for (String topNGram : topKSet.getKeys()) {
            if (documentNGrams.getKeys().contains(topNGram)) {
                features.add(new Feature(getFeaturePrefix() + "_" + topNGram, 1));
            }
        }

        return features;
    }

//    @Override
//    public List<MetaCollectorConfiguration> getMetaCollectorClasses(Map<String, Object> parameterSettings) throws ResourceInitializationException {
//        return Collections.singletonList(new MetaCollectorConfiguration(LuceneSkipNgramMetaCollector.class,
//                parameterSettings).addStorageMapping(
//                LuceneSkipNgramMetaCollector.PARAM_TARGET_LOCATION,
//                LuceneSkipNGram.PARAM_SOURCE_LOCATION,
//                LuceneSkipNgramMetaCollector.LUCENE_DIR));
//    }
}