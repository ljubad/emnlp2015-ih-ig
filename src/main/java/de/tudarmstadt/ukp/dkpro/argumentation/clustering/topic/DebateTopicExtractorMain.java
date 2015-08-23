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

package de.tudarmstadt.ukp.dkpro.argumentation.clustering.topic;

import org.apache.uima.fit.descriptor.ConfigurationParameter;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Estimates topic distribution for each debate and serializes the mapping debateUrl/topics to
 * a file
 *
 * @author Ivan Habernal
 */
@Deprecated // TODO delete
public class DebateTopicExtractorMain
//        extends JCasConsumer_ImplBase
{
    /**
     * Where the map document/topics will be serialized
     */
    public static final String PARAM_OUTPUT_FILE = "outputFile";
    @ConfigurationParameter(name = PARAM_OUTPUT_FILE, mandatory = true)
    File outputFile;

    private Map<String, List<Double>> debateTopicMap = new HashMap<>();
/*
    @Override
    public void process(JCas aJCas)
            throws AnalysisEngineProcessException
    {
        // extract topic distribution as a double list
        TopicDistribution topicDistribution = JCasUtil.selectSingle(aJCas, TopicDistribution.class);
        DoubleArray doubleArray = topicDistribution.getTopicProportions();
        double[] array = doubleArray.toArray();

        List<Double> list = new ArrayList<>(array.length);
        for (double anArray : array) {
            list.add(anArray);
        }

        // add url/topics to the map
        String documentId = DocumentMetaData.get(aJCas).getDocumentId();

        if (documentId == null) {
            throw new IllegalStateException("Document id is null");
        }

        if (debateTopicMap.containsKey(documentId)) {
            throw new IllegalStateException(
                    "Document " + documentId + " already present in the doc/topic map");
        }

        debateTopicMap.put(documentId, list);
    }

    @Override
    public void collectionProcessComplete()
            throws AnalysisEngineProcessException
    {
        super.collectionProcessComplete();

        // serialize the map
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(outputFile));
            os.writeObject(debateTopicMap);
            IOUtils.closeQuietly(os);
        }
        catch (IOException e) {
            throw new AnalysisEngineProcessException(e);
        }
    }

    public static void main(String[] args)
            throws UIMAException, IOException
    {
        String corpusPath = args[0];
        String topicModelLocation = args[1];
        String outputFile = args[2];

        SimplePipeline.runPipeline(
                // reader
                CollectionReaderFactory.createReaderDescription(
                        FullDebateContentReader.class,
                        FullDebateContentReader.PARAM_SOURCE_LOCATION,
                        corpusPath),

                // tokenize web-texts
                AnalysisEngineFactory.createEngineDescription(ArktweetTokenizerFixed.class),

                // lemmatizer
                AnalysisEngineFactory.createEngineDescription(StanfordLemmatizer.class),

                // sentence splitter
                AnalysisEngineFactory.createEngineDescription(StanfordSegmenter.class,
                        StanfordSegmenter.PARAM_WRITE_TOKEN, false),

                AnalysisEngineFactory.createEngineDescription(
                        DocumentTopicAnnotator.class,
                        DocumentTopicAnnotator.PARAM_USE_LEMMA, true,
                        DocumentTopicAnnotator.PARAM_MODEL_LOCATION, topicModelLocation
                ),
                AnalysisEngineFactory.createEngineDescription(
                        DebateTopicExtractorMain.class,
                        DebateTopicExtractorMain.PARAM_OUTPUT_FILE, outputFile
                )
        );
    }

*/
}
