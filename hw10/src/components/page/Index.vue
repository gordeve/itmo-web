<template>
    <div>
        <Article :post="posts[4]" :login="users.filter(u => u.id === posts[4].userId).login"></Article>
    </div>
</template>

<script>
import Article from "@/components/Article.vue";

export default {
    name: "Index",
    props: ["posts", "users", "comments"],
    components: {
      Article
    }
}
</script>

<style scoped>

</style>
