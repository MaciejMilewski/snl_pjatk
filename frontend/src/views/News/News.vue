<template>
  <div class="uk-section-default uk-section"
       uk-scrollspy="target: [uk-scrollspy-class]; cls: uk-animation-slide-left-medium; delay: false;" :key="page">

    <div class="uk-container" >


      <div class="tm-grid-expand uk-child-width-1-1 uk-grid-margin uk-grid uk-grid-stack" uk-grid="">
        <div class="uk-width-1-1 uk-first-column">
          <div id="tlh-aktualnosci-naglowek" class="uk-h1" uk-scrollspy-class> Aktualności</div>
          <div class="uk-width-1-1 uk-first-column">
            <div class="uk-margin">
              <div class="uk-child-width-1-1 uk-child-width-1-3@m uk-grid-match uk-grid uk-grid-stack" uk-grid="">
                  <div id="artykuly" v-for="article in news" v-bind:key="article.id"
                       uk-scrollspy="target: [uk-scrollspy-class2]; cls: uk-animation-slide-right-medium; delay: 500;">
                      <ArticleView uk-scrollspy-class2
                                   :id="article.id"
                                   :title="article.title"
                                   :author="article.author"
                                   :image="article.image"
                                   :date="article.date"
                                   :description="article.description"
                      />
                  </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <ul class="uk-pagination uk-grid-inline uk-flex-center">
    <li v-if="page !== 0">
      <a  class="uk-h2" @click="paginationPrevious">
        <span uk-pagination-previous="" class="uk-icon uk-pagination-previous"/>
        Poprzednie</a>
    </li>
    <li v-if="maximumPage !== true">
      <a  class="uk-h2" @click="paginationNext">Następne
        <span uk-pagination-next="" class="uk-icon uk-pagination-next"/>
      </a>
    </li>
  </ul>

</template>

<script>
import axios from "axios";
import ArticleView from "@/views/News/Articles/Article";
export default {
  name: "NewsView",
  components: {ArticleView},
  data() {
    return {
      news: [],
      page: 0,
      pagination: undefined,
      maximumPage: false,
    }
  },
  methods: {
    paginationPrevious(){
      if(this.page - 1 !== -1)this.page--
      this.getNews()
      this.maximumPage = false;
    },
    paginationNext(){
      if(this.news.length !== 1){
        this.page = this.page + 1;
        this.maximumPage = false;
      }else {
        this.maximumPage = true;
      }
      this.getNews()

    },
    getNews(){
      axios.get("http://localhost:8080/api/news?page="+this.page+"&size=3",)
          .then(response => {
            let data = response.data.data
            this.maximumPage = response.data.isLastPage;
            let newsData = [];
            if(data !== undefined)
            data.forEach((d) => {
              let dataUri = "data:image/png;base64," + d.image
              d.image = dataUri
              newsData.push(d)
            })
            this.news = newsData;
            if(this.news.length === 1 )this.maximumPage = true;
          })
    },

  },
  mounted() {
    this.getNews()
  }
}
</script>

<style scoped>
#tlh-aktualnosci-naglowek {
  padding: 10px;
  background: #a6efff;
  color: #006394;
  font-weight: bold;
  border-bottom-right-radius: 20px;
}

</style>