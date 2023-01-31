<template>
    <table class="uk-table uk-table-striped uk-table-hover">
      <thead>
      <tr>
        <th>Zdjęcie</th>
        <th>Id</th>
        <th>Autor</th>
        <th>Tytuł</th>
        <th>Edytuj</th>
        <th>Usuń</th>
      </tr>

      </thead>
      <tbody v-for="article in articles" :key="article.id" id="tlh-tabelka-zawodnicy">
      <NewsAdminView
                     :image="article.image"
                     :id="article.id"
                     :author="article.author"
                     :title="article.title"
                     @incrementKey="incrementKey"
      />

      </tbody>
    </table>
</template>

<script>
import NewsAdminView from "@/views/News/Admin/NewsAdminView";
export default {
  name: "NewsListAdmin",
  components: {NewsAdminView},
  props: ['articles'],
  setup(props,context){
    function incrementKey(){
      context.emit('incrementKey')
    }
    return {
      incrementKey,
    }
  }
}
</script>

<style scoped>
.uk-table-striped > tr:nth-of-type(odd) {
  background: #8debff;
}
.uk-table-hover > *  {
  background: #8debff;
}

#tlh-tabelka-zawodnicy > *:hover {
  background: #a6efff;
}
#tlh-tabelka-zawodnicy > tr > td > img {
  height: 100px;
}
</style>