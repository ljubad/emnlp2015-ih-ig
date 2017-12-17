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

import org.dkpro.tc.api.features.meta.MetaCollector;
import org.dkpro.tc.features.ngram.base.LuceneFeatureExtractorBase;

import java.util.HashSet;
import java.util.Set;

public abstract class LemmaLuceneNgramFeatureExtractorBase
        extends LuceneFeatureExtractorBase
{

    public Set<Class<? extends MetaCollector>> getMetaCollectorClasses()
    {
        Set<Class<? extends MetaCollector>> metaCollectorClasses = new HashSet<>();
        metaCollectorClasses.add(LemmaLuceneNGramMetaCollector.class);

        return metaCollectorClasses;
    }

    @Override
    protected String getFieldName()
    {
        return LUCENE_NGRAM_FIELD;
    }

    @Override
    protected String getFeaturePrefix()
    {
        return "ngram";
    }

    @Override
    protected int getTopN()
    {
        return ngramUseTopK;
    }
}