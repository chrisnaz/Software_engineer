<template>
    <v-layout column mt-5 ml-5 mr-5>
        <v-layout  align-start row>
            <v-flex>
                <h1 class="pink_custom--text"> My State of the Art  </h1>
            </v-flex>
        </v-layout>
    
    <v-layout column>
        <v-flex>
        <v-list>
     
            <v-list-tile v-for="article in listArticle" :key="article.titre" class=" mb-3">
                <v-layout row align-start justify-space-between >
                    <v-flex xs10 mt-1>
                        <v-layout column class="light_grey_background">
                            <v-flex>
                        <span> {{article.titre}} </span>
                            </v-flex>
                            <v-flex>
                            <span> {{article.author}}, {{article.coauthor}} </span>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    
                    <v-flex ml-3 xs2>
                        <v-layout row>
                        <v-btn to="/soa" large flat class="pink_custom" style="border-radius: 5px;"> 
                            <v-icon class="white--text"> launch </v-icon>
                        </v-btn>
                        </v-layout>
                    </v-flex>
                    
                </v-layout>

            </v-list-tile>
        </v-list>
        </v-flex>
    </v-layout>
    </v-layout> 
</template>

<script>
export default {
    data () {
        return {
            listArticle: [
                {titre : 'l\'informatique à l\'école', author: 'Petit Lard', coauthor:'Herta'},
                {titre : 'l\'informatique à l\'école', author: 'Petit Lard', coauthor:'Herta'},
                {titre : 'l\'informatique à l\'école', author: 'Petit Lard', coauthor:'Herta'},
                {titre : 'l\'informatique à l\'école', author: 'Petit Lard', coauthor:'Herta'},
                {titre : 'l\'informatique à l\'école', author: 'Petit Lard', coauthor:'Herta'},
                {titre : 'l\'informatique à l\'école', author: 'Petit Lard', coauthor:'Herta'}
                
            ]
        }
    }
    
}
</script>
