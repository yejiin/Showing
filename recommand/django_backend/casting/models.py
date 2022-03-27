from statistics import mode
from django.db import models


class Casting(models.Model):
    season_playdb_id = models.IntegerField()
    actor_playdb_id = models.IntegerField()
    role = models.CharField(max_length=255, null=True)
    create_date = models.DateTimeField(auto_now_add=True)
    update_date = models.DateTimeField(auto_now=True)

    class Meta:
        managed = False
        db_table = 'casting'
