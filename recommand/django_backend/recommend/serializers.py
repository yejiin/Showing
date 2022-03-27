from rest_framework import serializers
from recommend.models import Recommend


class RecommendSerializer(serializers.ModelSerializer):
    class Meta:
        model = Recommend
        fields = "__all__"
