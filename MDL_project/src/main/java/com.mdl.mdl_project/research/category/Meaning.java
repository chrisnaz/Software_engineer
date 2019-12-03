package com.mdl.mdl_project.research.category;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Andry Rajaona
 * @version 1.1
 * This class allow to do matching between number in cluster of state of art
 * and the legend
 */

public class Meaning {

    private String word;
    private Integer reference;

    public Meaning(String word, Integer reference) {
        this.word = word;
        this.reference = reference;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    /**
     * Make the link between cluster, legend, calculates for the cluster
     * @param keywords, list combination keyword
     * @return list Meaning
     */
    public static List<Meaning> meaningCluster(List<String> keywords) {
        List<Meaning> meanings = new ArrayList<>();
        if (keywords.isEmpty())
            return meanings;
        meanings.add(new Meaning(keywords.get(0), 1));
        if (keywords.size() > 1) {
            String keyword;
            Integer ref;
            for (int i = 1; i < keywords.size(); i++) {
                keyword = keywords.get(i);
                ref = i+1;
                meanings.add(new Meaning(keyword, ref));
            }

            for (int i = 1; i < keywords.size(); i++) {
                for (int j = i + 1; j < keywords.size(); j++) {
                    keyword = keywords.get(i)+" ; "+keywords.get(j);
                    ref = (i+1)*(j+1);
                    meanings.add(new Meaning(keyword, ref));
                }
            }

            if (keywords.size() > 3) {
                for (int i = 1; i < keywords.size(); i++) {
                    for (int j = i + 1; j < keywords.size(); j++) {
                        for (int k = j + 1; k < keywords.size(); k++) {
                            keyword = keywords.get(i)+" ; "+keywords.get(j)+" ; "+keywords.get(k);
                            ref = (i+1)*(j+1)*(k+1);
                            meanings.add(new Meaning(keyword, ref));
                        }
                    }
                }
            }

            if (keywords.size() > 4) {
                for (int i = 1; i < keywords.size(); i++) {
                    for (int j = i + 1; j < keywords.size(); j++) {
                        for (int k = j + 1; k < keywords.size(); k++) {
                            for (int l = k + 1; l < keywords.size(); l++) {
                                keyword = keywords.get(i)+" ; "+keywords.get(j)+" ; "+keywords.get(k)+" ; "+keywords.get(l);
                                ref = (i+1)*(j+1)*(k+1)*(l+1);
                                meanings.add(new Meaning(keyword, ref));
                            }
                        }
                    }
                }
            }

            if (keywords.size() > 5) {
                for (int i = 1; i < keywords.size(); i++) {
                    for (int j = i + 1; j < keywords.size(); j++) {
                        for (int k = j + 1; k < keywords.size(); k++) {
                            for (int l = k + 1; l < keywords.size(); l++) {
                                for (int m = l + 1; m < keywords.size(); m++) {
                                    keyword = keywords.get(i)+" ; "+keywords.get(j)+" ; "+keywords.get(k)+" ; "+keywords.get(l)+" ; "+keywords.get(m);
                                    ref = (i+1)*(j+1)*(k+1)*(l+1)*(m+1);
                                    meanings.add(new Meaning(keyword, ref));
                                }
                            }
                        }
                    }
                }
            }

            if (keywords.size() > 6) {
                for (int i = 1; i < keywords.size(); i++) {
                    for (int j = i + 1; j < keywords.size(); j++) {
                        for (int k = j + 1; k < keywords.size(); k++) {
                            for (int l = k + 1; l < keywords.size(); l++) {
                                for (int m = l + 1; m < keywords.size(); m++) {
                                    for (int n = m + 1; n < keywords.size(); n++) {
                                        keyword = keywords.get(i)+" ; "+keywords.get(j)+" ; "+keywords.get(k)+" ; "+keywords.get(l)+" ; "+keywords.get(m)+" ; "+keywords.get(n);
                                        ref = (i+1)*(j+1)*(k+1)*(l+1)*(m+1)*(n+1);
                                        meanings.add(new Meaning(keyword, ref));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return meanings;
    }

    /**
     * Creates the legend
     * @param keywords, list combination keyword
     * @return list the legend in string
     */
    public static List<String > cluster(List<String> keywords) {
        List<String> cluster = new ArrayList<>();
        if (keywords.isEmpty())
            return cluster;
        cluster.add(keywords.get(0));
        if (keywords.size() > 1) {
            String keyword;
            for (int i = 1; i < keywords.size(); i++) {
                keyword = keywords.get(i);
                cluster.add(keyword);
            }

            for (int i = 1; i < keywords.size(); i++) {
                for (int j = i + 1; j < keywords.size(); j++) {
                    keyword = keywords.get(i)+" ; "+keywords.get(j);
                    cluster.add(keyword);
                }
            }

            if (keywords.size() > 3) {
                for (int i = 1; i < keywords.size(); i++) {
                    for (int j = i + 1; j < keywords.size(); j++) {
                        for (int k = j + 1; k < keywords.size(); k++) {
                            keyword = keywords.get(i)+" ; "+keywords.get(j)+" ; "+keywords.get(k);
                            cluster.add(keyword);
                        }
                    }
                }
            }

            if (keywords.size() > 4) {
                for (int i = 1; i < keywords.size(); i++) {
                    for (int j = i + 1; j < keywords.size(); j++) {
                        for (int k = j + 1; k < keywords.size(); k++) {
                            for (int l = k + 1; l < keywords.size(); l++) {
                                keyword = keywords.get(i)+" ; "+keywords.get(j)+" ; "+keywords.get(k)+" ; "+keywords.get(l);
                                cluster.add(keyword);
                            }
                        }
                    }
                }
            }

            if (keywords.size() > 5) {
                for (int i = 1; i < keywords.size(); i++) {
                    for (int j = i + 1; j < keywords.size(); j++) {
                        for (int k = j + 1; k < keywords.size(); k++) {
                            for (int l = k + 1; l < keywords.size(); l++) {
                                for (int m = l + 1; m < keywords.size(); m++) {
                                    keyword = keywords.get(i)+" ; "+keywords.get(j)+" ; "+keywords.get(k)+" ; "+keywords.get(l)+" ; "+keywords.get(m);
                                    cluster.add(keyword);
                                }
                            }
                        }
                    }
                }
            }

            if (keywords.size() > 6) {
                for (int i = 1; i < keywords.size(); i++) {
                    for (int j = i + 1; j < keywords.size(); j++) {
                        for (int k = j + 1; k < keywords.size(); k++) {
                            for (int l = k + 1; l < keywords.size(); l++) {
                                for (int m = l + 1; m < keywords.size(); m++) {
                                    for (int n = m + 1; n < keywords.size(); n++) {
                                        keyword = keywords.get(i)+" ; "+keywords.get(j)+" ; "+keywords.get(k)+" ; "+keywords.get(l)+" ; "+keywords.get(m)+" ; "+keywords.get(n);
                                        cluster.add(keyword);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return cluster;
    }

    /**
     * number cluster corresponding of legend
     * @param keywords, list combination keyword
     * @return index list correspond cluster and value correspond cluster calculated
     */
    public static List<Integer> referenceCluster(List<String> keywords) {
        List<Integer> reference = new ArrayList<>();
        if (keywords.isEmpty())
            return reference;
        reference.add(1);
        if (keywords.size() > 1) {
            Integer ref;
            for (int i = 1; i < keywords.size(); i++) {
                ref = i+1;
                reference.add(ref);
            }

            for (int i = 1; i < keywords.size(); i++) {
                for (int j = i + 1; j < keywords.size(); j++) {
                    ref = (i+1)*(j+1);
                    reference.add(ref);
                }
            }

            if (keywords.size() > 3) {
                for (int i = 1; i < keywords.size(); i++) {
                    for (int j = i + 1; j < keywords.size(); j++) {
                        for (int k = j + 1; k < keywords.size(); k++) {
                            ref = (i+1)*(j+1)*(k+1);
                            reference.add(ref);
                        }
                    }
                }
            }

            if (keywords.size() > 4) {
                for (int i = 1; i < keywords.size(); i++) {
                    for (int j = i + 1; j < keywords.size(); j++) {
                        for (int k = j + 1; k < keywords.size(); k++) {
                            for (int l = k + 1; l < keywords.size(); l++) {
                                ref = (i+1)*(j+1)*(k+1)*(l+1);
                                reference.add(ref);
                            }
                        }
                    }
                }
            }

            if (keywords.size() > 5) {
                for (int i = 1; i < keywords.size(); i++) {
                    for (int j = i + 1; j < keywords.size(); j++) {
                        for (int k = j + 1; k < keywords.size(); k++) {
                            for (int l = k + 1; l < keywords.size(); l++) {
                                for (int m = l + 1; m < keywords.size(); m++) {
                                    ref = (i+1)*(j+1)*(k+1)*(l+1)*(m+1);
                                    reference.add(ref);
                                }
                            }
                        }
                    }
                }
            }

            if (keywords.size() > 6) {
                for (int i = 1; i < keywords.size(); i++) {
                    for (int j = i + 1; j < keywords.size(); j++) {
                        for (int k = j + 1; k < keywords.size(); k++) {
                            for (int l = k + 1; l < keywords.size(); l++) {
                                for (int m = l + 1; m < keywords.size(); m++) {
                                    for (int n = m + 1; n < keywords.size(); n++) {
                                        ref = (i+1)*(j+1)*(k+1)*(l+1)*(m+1)*(n+1);
                                        reference.add(ref);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return reference;
    }

}

