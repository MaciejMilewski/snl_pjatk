<template>
  <div>
    <div uk-grid id="tlh-aktualnosci-grid">
      <div class="uk-width-auto@m" id="tlh-aktualnosci-uk-width-auto">
        <ul class="uk-tab-left" uk-tab="connect: #komponent-tab-lewo-aktualnosci; animation: uk-animation-fade">
          <li @click="incrementKeyParent"><a  class="news-tab-left" ><span uk-icon="icon: file-text" />Artykuły</a></li>
          <li><a class="news-tab-left" ><span uk-icon="icon: pencil" />Dodaj artykuł</a></li>
        </ul>
      </div>
      <div class="uk-width-expand@m" id="tlh-komponent-tab">
        <ul id="komponent-tab-lewo-aktualnosci" class="uk-switcher">
          <li>
            <NewsListAdmin
                :articles="articles"
                @incrementKey="incrementKeyParent"
            />
          </li>
          <li>
            <ArticleFormComponent class="uk-width-1-1@l"
                                  :articles="articles"
            />
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import blobToDataURL from "@/assets/scripts/blobToDataURL";
import NewsListAdmin from "@/views/News/Admin/NewsListAdmin";
import ArticleFormComponent from "@/components/Article/ArticleForm";
export default {
  name: "NewsTabView",
  components: {NewsListAdmin, ArticleFormComponent},
  data(){
    return {
      articles: []
    }
  },
  created() {
    axios.get("http://localhost:8080/api/news/all")
        .then(response => {
          const data = response.data.news;
          if (data !== undefined)
            data.forEach((d) => {
              if (d.image !== null) {
                d.image = blobToDataURL(d.image)
              }
            })
          this.articles = data;
        })
  },
  methods: {
    incrementKeyParent(){
      this.$emit('incrementKey')
    }
  },
}
</script>

<style scoped>
#tlh-aktualnosci-grid {
  background-color: #8debff;
  border-top-left-radius: 10px;
}
#tlh-aktualnosci-uk-width-auto {
  background-color: #a6efff;
  border-top-left-radius: 10px;
}
.uk-tab {
  background-color: #a6efff;
  padding: 10px;

  height: max-content !important;
}
.uk-tab > * > a:hover {
  color: #006394;
  font-weight: bold;
}
.uk-tab > .uk-active > a {
  font-weight: bold;
  color: #006394 !important;
  border-color: #006394;
  border-right-width: 5px;
}
</style>