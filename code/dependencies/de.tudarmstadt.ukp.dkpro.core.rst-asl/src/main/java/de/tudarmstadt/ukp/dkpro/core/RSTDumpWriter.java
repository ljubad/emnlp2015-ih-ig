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

package de.tudarmstadt.ukp.dkpro.core;

import de.tudarmstadt.ukp.dkpro.core.rst.core.type.EDU;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasConsumer_ImplBase;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

/**
 * Very simplistic debugger of annotated EDUs to stdout
 *
 * @author Ivan Habernal
 */
public class RSTDumpWriter
        extends JCasConsumer_ImplBase
{
    @Override
    public void process(JCas aJCas)
            throws AnalysisEngineProcessException
    {
        System.out.println(JCasUtil.select(aJCas, EDU.class).size() + " EDUs found");
    }
}
