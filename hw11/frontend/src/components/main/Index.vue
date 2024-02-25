<template>
    <div>
        <Article v-for="post in posts" :post="post" :key="post.id"/>
    </div>
</template>

<script>
import Article from '../Article';

export default {
    name: "Index",
    components: {
        Article
    },
    props: ["posts"]
}
</script>

<style scoped>

</style>