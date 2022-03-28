from operator import mod
from rest_framework import serializers

from casting.models import Casting


class CastingSerializer(serializers.ModelSerializer):
    class Meta:
        model = Casting
        fields = "__all__"
